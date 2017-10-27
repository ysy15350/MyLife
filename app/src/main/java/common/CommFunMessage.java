package common;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ysy15350.mylife.R;

public class CommFunMessage {

    public static Context mContext;

    private static long lastClickTime;

    private static long lastShowTime;

    private static long lastFreshTime;

    /**
     * 防止多次点击
     *
     * @return
     */
    public synchronized static boolean isFastClick() {
        long time = System.currentTimeMillis();
        if (time - lastClickTime < 1000) {
            return true;
        }
        lastClickTime = time;
        return false;
    }

    /**
     * 防止频繁提示相同消息
     *
     * @return
     */
    public synchronized static boolean isFastShow() {
        long time = System.currentTimeMillis();
        if (time - lastShowTime < 3000) {
            return true;
        }
        lastShowTime = time;
        return false;
    }

    /**
     * 防止频繁刷新
     *
     * @return
     */
    public synchronized static boolean isFastFresh() {
        long time = System.currentTimeMillis();
        if (time - lastFreshTime < 10000) {
            return true;
        }
        lastFreshTime = time;
        return false;
    }

    private static String m_message = "";

    /**
     * 系统提示
     *
     * @param c       当前Activity
     * @param message 提示信息
     */
    public static void showMsgBox(Context context, String message) {
        if (context != null)
            mContext = context;
        if (mContext != null) {
            if (isFastShow() && m_message.equals(message)) {
                return;
            }

            m_message = message;

            Toast toast = Toast.makeText(mContext, message, Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.TOP, 0, 200);
            toast.show();
        }
    }

    /**
     * 消息提示
     *
     * @param message
     */
    public static void showMsgBox(String message) {
        if (mContext != null && !CommFunAndroid.isNullOrEmpty(message)) {
            Toast toast = Toast.makeText(mContext, message, Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.TOP, 0, 200);
            toast.show();
        }
    }

    /**
     * 系统提示
     *
     * @param c       当前Activity
     * @param message 提示信息
     */
    public static void showImgBox(Context context, int pic_id) {

        try {
            if (context != null)
                mContext = context;
            if (mContext != null) {
                // 创建新Toast对象
                Toast showImageToast = new Toast(mContext);

                // 创建新ImageView对象
                ImageView imageView = new ImageView(mContext);

                // 从资源中获取图片
                imageView.setImageResource(pic_id);

                // 设置Toast上的View(imageView)
                showImageToast.setView(imageView);

                // 设置Toast显示时间
                showImageToast.setDuration(Toast.LENGTH_SHORT);

                // 显示Toast
                showImageToast.show();
            }
        } catch (Exception e) {

        }

    }

    /**
     * 系统提示,图片加文字
     *
     * @param c       当前Activity
     * @param message 提示信息
     */
    public static void showCustomMsg(Context context, int pic_id, String msg) {

        try {
            if (context != null)
                mContext = context;
            if (mContext != null) {
                LayoutInflater mInflater = LayoutInflater.from(mContext);
                ViewGroup rootView = (ViewGroup) mInflater.inflate(R.layout.dialog_message, null);
                rootView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));

                TextView tv_message = (TextView) rootView.findViewById(R.id.tv_message);
                if (!msg.equals(""))
                    tv_message.setText(msg);
                ImageView img_icon = (ImageView) rootView.findViewById(R.id.img_icon);
                if (img_icon != null && pic_id != 0) {
                    img_icon.setImageResource(pic_id);
                }

                // 创建新Toast对象
                Toast showImageToast = new Toast(mContext);

                // 设置Toast上的View(imageView)
                showImageToast.setView(rootView);

                // 设置Toast显示时间
                showImageToast.setDuration(Toast.LENGTH_SHORT);
                // showImageToast.setGravity(Gravity.TOP, 0, 200);

                // 显示Toast
                showImageToast.show();

            }
        } catch (Exception e) {
            // TODO: handle exception
        }

    }

    /**
     * 自定义确认对话框
     *
     * @param context          上下文
     * @param message          消息
     * @param confirmListenser 监听
     */
    public static void showCustomConfirm(Context context, String message, CustomConfirmListenser confirmListenser) {
        showCustomConfirm(context, "系统提示", message, "确认", "取消", 2, confirmListenser);
    }

    /**
     * 自定义确认对话框
     *
     * @param title           对话框标题
     * @param message         显示内容
     * @param btn_ok_text     确认按钮文本
     * @param btn_cancel_text 取消按钮文本
     * @param count           按钮个数：1,2
     */
    public static void showCustomConfirm(Context context, String title, String message, String btn_ok_text,
                                         String btn_cancel_text, int count, CustomConfirmListenser confirmListenser) {

        try {
            if (context != null)
                mContext = context;
            if (confirmListenser != null) {
                mConfirmListenser = confirmListenser;
            }
            if (mContext != null) {
                LayoutInflater mInflater = LayoutInflater.from(mContext);
                ViewGroup rootView = (ViewGroup) mInflater.inflate(R.layout.dialog_confirm, null);
                rootView.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
                TextView tv_title = (TextView) rootView.findViewById(R.id.tv_title);
                if (!title.equals(""))
                    tv_title.setText(title);
                TextView tv_content = (TextView) rootView.findViewById(R.id.tv_content);

                tv_content.setText(message);

                final Dialog dialog = new Dialog(mContext);

                WindowManager.LayoutParams params = dialog.getWindow().getAttributes();
                params.width = WindowManager.LayoutParams.WRAP_CONTENT;
                params.height = WindowManager.LayoutParams.WRAP_CONTENT;

                //dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.R.color.transparent));
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#b0000000")));
                dialog.setCanceledOnTouchOutside(false);

                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(rootView);

                Button btn_ok = (Button) rootView.findViewById(R.id.btn_ok);
                if (!btn_ok_text.equals(""))
                    btn_ok.setText(btn_ok_text);

                btn_ok.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View arg0) {
                        if (mConfirmListenser != null)
                            mConfirmListenser.okClick();

                        dialog.dismiss();

                    }
                });

                Button btn_cancel = (Button) rootView.findViewById(R.id.btn_cancel);

                if (count == 1)
                    btn_cancel.setVisibility(View.GONE);

                if ("".equals(btn_cancel_text))
                    btn_cancel.setText(btn_cancel_text);

                btn_cancel.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View arg0) {
                        if (mConfirmListenser != null)
                            mConfirmListenser.cancelClick();

                        dialog.dismiss();

                    }
                });

                // dialog.getWindow().setGravity(Gravity.LEFT | Gravity.TOP);

                params.gravity = Gravity.CENTER_HORIZONTAL;

                dialog.show();
            }
        } catch (Exception e) {
            // TODO: handle exception
            String ss = e.getMessage();

        }

    }

    /**
     * 确认对话框监听
     */
    private static CustomConfirmListenser mConfirmListenser;

    /**
     * 设置自定义确认对话框监听
     *
     * @param customConfirmListenser
     */
    public void setConfirmListenser(CustomConfirmListenser customConfirmListenser) {
        mConfirmListenser = customConfirmListenser;
    }

    /**
     * 自定义确认框监听
     *
     * @author Administrator
     */
    public interface CustomConfirmListenser {
        void okClick();

        void cancelClick();
    }

}