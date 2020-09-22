#!/bin/sh
set -e

# Script inspired by https://gist.github.com/szeidner/613fe4652fc86f083cefa21879d5522b

PROGNAME=$(basename $0)
WORKING_DIR=$(cd -P -- "$(dirname -- "$0")" && pwd -P)
OLD_APPNAME="Base Template"
OLD_TITLE="template"
OLD_PACKAGE="io.bloco.template"

die() {
    echo "$PROGNAME: $*" >&2
    exit 1
}

usage() {
    if [ "$*" != "" ] ; then
        echo "Error: $*"
    fi

    cat << EOF
Usage: $PROGNAME --package-name [PACKAGE_NAME] --title [TITLE] --app-name [APP_NAME]
Rename an Android app and package.

Options:
-h, --help                         display this usage message and exit
-p, --package-name [PACKAGE_NAME]  new package name (i.e. com.example.package)
-t, --title [TITLE]                new app title (i.e. MyApp)

EOF

    exit 1
}

packagename=""
title=""
while [ $# -gt 0 ] ; do
    case "$1" in
    -h|--help)
        usage
        ;;
    -p|--package-name)
        packagename="$2"
        shift
        ;;
    -t|--title)
        title="$2"
        shift
        ;;
    -*)
        usage "Unknown option '$1'"
        ;;
    *)
        usage "Too many arguments"
      ;;
    esac
    shift
done

if [ -z "$packagename" ] ; then
    usage "No new package provided"
fi

if [ -z "$title" ] ; then
    usage "No Title provided"
fi

# Enforce package name
regex='^[a-z][a-z0-9_]*(\.[a-z0-9_]+)+[0-9a-z_]$'
if ! [[ $packagename =~ $regex ]]; then
    die "Invalid Package Name: $packagename (needs to follow standard pattern {com.example.package})"
fi

# Enforce title 
if [[ "$title" =~ \ |\' ]]; then
    die "Invalid Title: $title (can't have any spaces)"
fi

TITLE_NO_SPACES=$title

# Copy main folder
cp -R $OLD_TITLE $TITLE_NO_SPACES

# get rid of idea settings
rm -rf $TITLE_NO_SPACES/.idea
# get rid of gradle cache
rm -rf $TITLE_NO_SPACES/.gradle
# get rid of the git history
rm -rf $TITLE_NO_SPACES/.git
# get rid of the build
rm -rf $TITLE_NO_SPACES/build
rm -rf $TITLE_NO_SPACES/app/build

# Rename folder structure
renameFolderStructure() {
  DIR=""
  if [ "$*" != "" ] ; then
      DIR="$*"
  fi
  ORIG_DIR=$DIR

  mv $TITLE_NO_SPACES/$DIR/io/bloco/template $TITLE_NO_SPACES/$DIR/
  rm -rf $TITLE_NO_SPACES/$DIR/io/bloco
  rm -rf $TITLE_NO_SPACES/$DIR/io
  cd $TITLE_NO_SPACES/$DIR
  IFS='.' read -ra packages <<< "$packagename"
  for i in "${packages[@]}"; do
      DIR="$DIR/$i"
      mkdir $i
      cd $i
  done
  mv $WORKING_DIR/$TITLE_NO_SPACES/$ORIG_DIR/template/* ./
  rmdir $WORKING_DIR/$TITLE_NO_SPACES/$ORIG_DIR/template
  cd $WORKING_DIR
  echo $DIR
}

# Rename project folder structure
PACKAGE_DIR="app/src/main/java"
PACKAGE_DIR=$( renameFolderStructure $PACKAGE_DIR )

# Rename android test folder structure
ANDROIDTEST_DIR="app/src/androidTest/java"
ANDROIDTEST_DIR=$( renameFolderStructure $ANDROIDTEST_DIR )

# Rename test folder structure
TEST_DIR="app/src/test/java"
TEST_DIR=$( renameFolderStructure $TEST_DIR )

echo "Files structure has been renamed. Replacing package and package name within files..."

# search and replace in files
PACKAGE_NAME_ESCAPED="${packagename//./\.}"
OLD_PACKAGE_NAME_ESCAPED="${OLD_PACKAGE//./\.}"
LC_ALL=C find $WORKING_DIR/$TITLE_NO_SPACES -type f -exec sed -i "" "s/$OLD_PACKAGE_NAME_ESCAPED/$PACKAGE_NAME_ESCAPED/g" {} +
LC_ALL=C find $WORKING_DIR/$TITLE_NO_SPACES -type f -exec sed -i "" "s/$OLD_TITLE/$TITLE_NO_SPACES/g" {} +

# search and replace files <...>

echo "Package and package name within files has been renamed, replacing strings.xml"
sed -i "" "s/$OLD_APPNAME/$title/" "$WORKING_DIR/$TITLE_NO_SPACES/app/src/main/res/values/strings.xml"
echo "Strings.xml app name replaced with $title, app is ready to be tested"


