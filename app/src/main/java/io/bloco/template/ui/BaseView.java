package io.bloco.template.ui;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.LayoutRes;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import butterknife.ButterKnife;
import io.bloco.template.common.di.ViewComponent;
import io.bloco.template.common.di.ViewModule;

public abstract class BaseView extends FrameLayout {

  public BaseView(Context context) {
    super(context);
    init(context);
  }

  public BaseView(Context context, AttributeSet attrs) {
    super(context, attrs);
    init(context);
  }

  public BaseView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    init(context);
  }

  @Override protected Parcelable onSaveInstanceState() {
    Bundle bundle = new Bundle();
    onSaveInstanceStateBundle(bundle);
    bundle.putParcelable("instanceState", super.onSaveInstanceState());
    return bundle;
  }

  @Override public void onRestoreInstanceState(Parcelable state) {
    if (state instanceof Bundle) {
      Bundle bundle = (Bundle) state;
      state = bundle.getParcelable("instanceState");
      onRestoreInstanceStateBundle(bundle);
    }
    super.onRestoreInstanceState(state);
  }

  protected abstract @LayoutRes int getLayoutRes();

  protected void onSaveInstanceStateBundle(Bundle bundle) {
  }

  protected void onRestoreInstanceStateBundle(Bundle bundle) {
  }

  protected ViewComponent getViewComponent() {
    BaseActivity activity = (BaseActivity) getContext();
    return activity.getActivityComponent().plus(new ViewModule(this));
  }

  private void init(Context context) {
    setLayoutParams(
        new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
    inflate(context, getLayoutRes(), this);
    ButterKnife.bind(this);
  }
}