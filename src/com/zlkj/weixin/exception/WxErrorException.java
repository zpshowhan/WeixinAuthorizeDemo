package com.zlkj.weixin.exception;
import com.zlkj.weixin.entity.WxError;
/**
 * 自定义微信异常
 */
public class WxErrorException extends Exception {
  private static final long serialVersionUID = -6357149550353160810L;
  private WxError error;

  public WxErrorException(WxError error) {
    super(error.toString());
    this.error = error;
  }

  public WxErrorException(WxError error, Throwable cause) {
    super(error.toString(), cause);
    this.error = error;
  }

  public WxError getError() {
    return this.error;
  }


}
