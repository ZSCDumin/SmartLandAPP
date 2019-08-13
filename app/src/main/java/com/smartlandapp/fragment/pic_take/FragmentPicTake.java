package com.smartlandapp.fragment.pic_take;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.smartlandapp.CameraPreview;
import com.smartlandapp.R;
import com.smartlandapp.ui.TopNavigation;
import com.smartlandapp.utility.PhotoUtils;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

public class FragmentPicTake extends Fragment {

    PhotoUtils photoUtils = new PhotoUtils();
    Bitmap picBitmap = null;
    private Camera mCamera;
    private ImageView imageView;
    private CameraPreview mPreview;
    private FrameLayout mCameralayout;
    private ImageView mTakePictureBtn;
    private int mCameraId = 0; //后置摄像头ID为0

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_pic, container, false);
        return root;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //判断是否相机支持
        if (!photoUtils.checkCameraHardware(getActivity())) {
            Toast.makeText(getActivity(), "相机不支持", Toast.LENGTH_SHORT)
                    .show();
        } else {
            //开启相机
            openCamera();
            mTakePictureBtn = (ImageView) getActivity().findViewById(R.id.btn_pic);
            imageView = (ImageView) getActivity().findViewById(R.id.pic_show);
            mTakePictureBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //聚焦后开始拍照
                    mCamera.autoFocus(mAutoFocusCallback);
                }
            });
            //设置相机竖屏
            photoUtils.setCameraDisplayOrientation(getActivity(), mCameraId, mCamera);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Bundle bundle = new Bundle();
                    bundle.putInt("key", R.string.pic_result);
                    //切换拍摄结果界面
                    Intent intent = new Intent(getActivity(), TopNavigation.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            });
        }
    }

    /**
     * 开启相机预览功能
     */
    public void openCamera() {
        if (mCamera == null) {
            //获取相机实例
            mCamera = photoUtils.getCameraInstance(mCameraId);
            mPreview = new CameraPreview(getActivity(), mCamera);
            //增加聚焦功能
            mPreview.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    mCamera.autoFocus(null);
                    return false;
                }
            });
            mCameralayout = (FrameLayout) getActivity().findViewById(R.id.camera_preview);
            mCameralayout.addView(mPreview);
            mCamera.startPreview();
        }
    }

    /**
     * 释放相机资源
     */
    public void releaseCamera() {
        if (mCamera != null) {
            mCamera.setPreviewCallback(null);
            mCamera.stopPreview();
            mCamera.release();
            mCamera = null;
        }
    }

    /**
     * 聚焦相机回调
     */
    private Camera.AutoFocusCallback mAutoFocusCallback = new Camera.AutoFocusCallback() {
        @Override
        public void onAutoFocus(boolean success, Camera camera) {
            if (success) {
                mCamera.takePicture(null, null, mPictureCallback);
            }
        }
    };

    /**
     * 拍照回调
     */
    private Camera.PictureCallback mPictureCallback = new Camera.PictureCallback() {
        @Override
        public void onPictureTaken(final byte[] data, Camera camera) {

            //获取照片存储路径
            File fileDir = new File(Environment
                    .getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM)
                    .getAbsoluteFile()
                    + File.separator
                    + "Camera"
                    + File.separator);
            if (!fileDir.exists()) {
                fileDir.mkdir();
            }
            // 以拍摄时间命名照片
            String fileName = new DateFormat().format("yyyyMMddHHmmss", new Date())
                    .toString() + ".jpg";
            new Thread(new Runnable() {
                @Override
                public void run() {
                    File file = new File(fileDir, fileName);
                    //Log.d("hello path is ", file.getAbsolutePath());
                    try {
                        // 获取图片
                        Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);

                        // 旋转图片
                        bitmap = photoUtils.rotateBitmapByDegree(bitmap, 90);
                        BufferedOutputStream bos = new BufferedOutputStream(
                                new FileOutputStream(file));
                        // 压缩图片
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 0, bos);
                        // 压缩图片
                        BitmapFactory.Options options = new BitmapFactory.Options();
                        options.inSampleSize = 2;
                        picBitmap = BitmapFactory.decodeByteArray(data, 0, data.length, options);
                        picBitmap = photoUtils.rotateBitmapByDegree(picBitmap, 90);
                        bos.flush();
                        bos.close();
                        bitmap.recycle();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    /*
                    try {
                        String url = MediaStore.Images.Media.insertImage(getContext().getContentResolver(),
                                file.getAbsolutePath(), fileName, null);

                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    getContext().sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse(file.getAbsolutePath())));
                    */
                }
            }).start();
            //拍照与显示不同步，会延后一张照片
            imageView.setImageBitmap(picBitmap);
            mCamera.startPreview();
        }
    };

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        imageView.setImageBitmap(null);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        releaseCamera();
    }
}