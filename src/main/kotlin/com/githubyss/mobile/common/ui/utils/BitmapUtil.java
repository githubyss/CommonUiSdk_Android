// package com.githubyss.mobile.common.ui.utils;
//
// import android.content.ContentUris;
// import android.content.res.Resources;
// import android.graphics.Bitmap;
// import android.graphics.BitmapFactory;
// import android.graphics.Canvas;
// import android.graphics.Matrix;
// import android.graphics.Paint;
// import android.graphics.PorterDuff;
// import android.graphics.PorterDuffXfermode;
// import android.graphics.Rect;
// import android.graphics.RectF;
// import android.net.Uri;
// import android.provider.DocumentsContract;
// //
//
// /**
//  * @Title:
//  * @Description:
//  * @Author:13071483 jiangry
//  * @Since:2013-8-8
//  * @Version:
//  */
// public class BitmapUtil {
//     private static final String TAG: String = "BitmapUtil";
//
//     /**
//      * 图片圆角处理
//      *
//      * @param bitmap
//      * @param roundPX
//      * @return
//      */
//     public static Bitmap getRCB(Bitmap bitmap, float roundPX) {
//         // RCB means
//         // Rounded
//         // Corner Bitmap
//         Bitmap dstbmp = Bitmap.createBitmap(bitmap.getWidth(),
//                 bitmap.getHeight(), Bitmap.Config.ARGB_8888);
//         Canvas canvas = new Canvas(dstbmp);
//
//         final int   color = 0xff424242;
//         final Paint paint = new Paint();
//         final Rect  rect  = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
//         final RectF rectF = new RectF(rect);
//         paint.setAntiAlias(true);
//         canvas.drawARGB(0, 0, 0, 0);
//         paint.setColor(color);
//         canvas.drawRoundRect(rectF, roundPX, roundPX, paint);
//         paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
//         canvas.drawBitmap(bitmap, rect, rect, paint);
//         return dstbmp;
//     }
//
//     /**
//      * 复制原图
//      * @param bitmap
//      * @return
//      */
//     public static Bitmap copyNewBitmap(Bitmap bitmap) {
//         // RCB means
//         // Rounded
//         // Corner Bitmap
//         Bitmap dstbmp = Bitmap.createBitmap(bitmap.getWidth(),
//                 bitmap.getHeight(), Bitmap.Config.ARGB_8888);
//         Canvas canvas = new Canvas(dstbmp);
//
//         final int color = 0xff424242;
//         final Paint paint = new Paint();
//         final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
//         final RectF rectF = new RectF(rect);
//         paint.setAntiAlias(true);
//         canvas.drawARGB(0, 0, 0, 0);
//         paint.setColor(color);
//         canvas.drawRect(rectF,paint);
//         paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
//         canvas.drawBitmap(bitmap, rect, rect, paint);
//         return dstbmp;
//     }
//
//
//
//     /**
//      * by Apricot
//      * @param mBitmap 分享链接的图片
//      * @return QQ及QZone分享图片的本地路径
//      */
//     public static String getQQShareImgLocalPath(Bitmap mBitmap){
//         saveBitmapToSdcard("QQShare",mBitmap);
//         String mPath = Environment.getExternalStorageDirectory()
//                 + "//SNEPA/QQShare.jpg";
//         File f = new File(mPath);
//         if(!f.exists()){
//             mPath = "";
//         }
//         return mPath;
//     }
//
//     public static File convertBitmap2File(String name, Bitmap mBitmap, Bitmap.CompressFormat fileType) {
//         File imageFile = null;
//
//         if(EPApp.getApp() != null
//                 && EPApp.getApp().getExternalCacheDir() != null
//                 && !isEmpty(EPApp.getApp().getExternalCacheDir().getPath())){
//             try {
//                 imageFile = new File(EPApp.getApp().getExternalCacheDir().getPath(), name + fileType);
//                 imageFile.createNewFile();
//                 FileOutputStream out = new FileOutputStream(imageFile);
// //                mBitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
//                 mBitmap.compress(fileType, 60, out);
//                 out.flush();
//                 out.close();
//             } catch (FileNotFoundException e) {
//                 e.printStackTrace();
//             } catch (IOException e) {
//                 e.printStackTrace();
//             }
//         }
//
//         return imageFile;
//     }
//
//     /**
//      * 获取图片大小
//      * @param bitmap
//      * @return
//      */
//     public static long getBitmapSize(Bitmap bitmap) {
//
//         if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR1) {
//             return bitmap.getByteCount();
//         }
//         return bitmap.getRowBytes() * bitmap.getHeight();
//     }
//
//     public static Bitmap getBitmapFromSdcard(String name) {
//         Bitmap bitmap = null;
//         try {
//             String filePathName = Environment.getExternalStorageDirectory() + "/SNEPA/";
//             bitmap = BitmapFactory.decodeFile(filePathName + name + ".jpg");
//         } catch (Exception e) {
//             logE(TAG, e.getMessage());
//         }
//
//         return bitmap;
//     }
//
//
//     public static String getPath(final Context context, final Uri uri) {
//
//         final boolean isKitKat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;
//
//         // DocumentProvider
//         if (isKitKat && DocumentsContract.isDocumentUri(context, uri)) {
//             // ExternalStorageProvider
//             if (isExternalStorageDocument(uri)) {
//                 final String docId = DocumentsContract.getDocumentId(uri);
//                 final String[] split = docId.split(":");
//                 final String type = split[0];
//
//                 if ("primary".equalsIgnoreCase(type)) {
//                     return Environment.getExternalStorageDirectory() + "/" + split[1];
//                 }
//
//
//             }
//             // DownloadsProvider
//             else if (isDownloadsDocument(uri)) {
//
//                 final String id = DocumentsContract.getDocumentId(uri);
//                 final Uri contentUri = ContentUris.withAppendedId(
//                         Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));
//
//                 return getDataColumn(context, contentUri, null, null);
//             }
//             // MediaProvider
//             else if (isMediaDocument(uri)) {
//                 final String docId = DocumentsContract.getDocumentId(uri);
//                 final String[] split = docId.split(":");
//                 final String type = split[0];
//
//                 Uri contentUri = null;
//                 if ("image".equals(type)) {
//                     contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
//                 } else if ("video".equals(type)) {
//                     contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
//                 } else if ("audio".equals(type)) {
//                     contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
//                 }
//
//                 final String selection = "_id=?";
//                 final String[] selectionArgs = new String[]{
//                         split[1]
//                 };
//
//                 return getDataColumn(context, contentUri, selection, selectionArgs);
//             }
//         }
//         // MediaStore (and general)
//         else if ("content".equalsIgnoreCase(uri.getScheme())) {
//
//             // Return the remote address
//             if (isGooglePhotosUri(uri))
//                 return uri.getLastPathSegment();
//
//             return getDataColumn(context, uri, null, null);
//         }
//         // File
//         else if ("file".equalsIgnoreCase(uri.getScheme())) {
//             return uri.getPath();
//         }
//
//         return null;
//     }
//
//
//     public static String getDataColumn(Context context, Uri uri, String selection,
//                                        String[] selectionArgs) {
//
//         Cursor cursor = null;
//         final String column = "_data";
//         final String[] projection = {
//                 column
//         };
//
//         try {
//             cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs,
//                     null);
//             if (cursor != null && cursor.moveToFirst()) {
//                 final int index = cursor.getColumnIndexOrThrow(column);
//                 return cursor.getString(index);
//             }
//         } finally {
//             if (cursor != null)
//                 cursor.close();
//         }
//         return null;
//     }
//
//
//     public static boolean isExternalStorageDocument(Uri uri) {
//         return "com.android.externalstorage.documents".equals(uri.getAuthority());
//     }
//
//
//     public static boolean isDownloadsDocument(Uri uri) {
//         return "com.android.providers.downloads.documents".equals(uri.getAuthority());
//     }
//
//
//     public static boolean isMediaDocument(Uri uri) {
//         return "com.android.providers.media.documents".equals(uri.getAuthority());
//     }
//
//
//     public static boolean isGooglePhotosUri(Uri uri) {
//         return "com.google.android.apps.photos.content".equals(uri.getAuthority());
//     }
//
//
//     public static boolean checkFileSize(File file) {
//
//         long size = 0;
//         //上传图片大小限制为一兆以内
//         long max = 1024 * 1024 * 5;
//         FileInputStream inputStream = null;
//         try {
//
//             if (file != null) {
//                 inputStream = new FileInputStream(file);
//                 size = inputStream.available();
//             }
//
//         } catch (Exception e) {
//             logE(TAG, e);
//         } finally {
//             if (null != inputStream) {
//                 try {
//                     inputStream.close();
//                 } catch (IOException e) {
//                     logE(TAG, e);
//                 }
//             }
//         }
//
//         //不合格
//         return size < max;
//     }
//
//     public static boolean checkFileSize(File file, int maxSize) {
//         long size = 0;
//         long max = 1024 * 1024 * maxSize;
//         FileInputStream inputStream = null;
//         try {
//             if (file != null) {
//                 inputStream = new FileInputStream(file);
//                 size = inputStream.available();
//             }
//         } catch (Exception e) {
//             e(TAG, e);
//         } finally {
//             if (null != inputStream) {
//                 try {
//                     inputStream.close();
//                 } catch (IOException e) {
//                     e(TAG, e);
//                 }
//             }
//         }
//         //不合格
//         return size < max;
//     }
//
//     public static boolean checkFileType(File file) {
//         String type = mFileTypes.get(getFileHeader(file));
//         if (TextUtils.isEmpty(type)) {//规定以外的文件类型
//             return false;
//         } else {
//             return true;
//         }
//
//     }
//
//     public static String getFileType(File file) {
//         return mFileTypes.get(getFileHeader(file));
//     }
//
//     public static String getFileHeader(File file) {
//         FileInputStream is = null;
//         String value = null;
//         try {
//             is = new FileInputStream(file);
//             byte[] b = new byte[3];
//             is.read(b, 0, b.length);
//             value = bytesToHexString(b);
//         } catch (Exception e) {
//             e.printStackTrace();
//         } finally {
//             if (null != is) {
//                 try {
//                     is.close();
//                 } catch (IOException e) {
//                 }
//             }
//         }
//         return value;
//     }
//
//     public static final HashMap<String, String> mFileTypes = new HashMap<String, String>();
//
//     static {
//         //images
//         mFileTypes.put("FFD8FF", "jpg");
//         mFileTypes.put("424D", "bmp");
//     }
//
//     //h5照片库选择图片
//     private static final HashMap<String, String> mH5BitmapTypes = new HashMap<String, String>();
//
//     static {
//         //images
//         mH5BitmapTypes.put("FFD8FF", "jpg");
//         mH5BitmapTypes.put("424D", "bmp");
//         //png头为 89504E47 取前6为判断
//         mH5BitmapTypes.put("89504E", "png");
//     }
//
//     public static boolean checkH5BitmapType(File file) {
//         String type = mH5BitmapTypes.get(getFileHeader(file));
//         if (TextUtils.isEmpty(type)) {//规定以外的文件类型
//             return false;
//         } else {
//             return true;
//         }
//
//     }
//
//     public static int readPictureDegree(String path) {
//         int degree = 0;
//         try {
//             ExifInterface exifInterface = new ExifInterface(path);
//             int orientation = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
//             switch (orientation) {
//                 case ExifInterface.ORIENTATION_ROTATE_90:
//                     degree = 90;
//                     break;
//                 case ExifInterface.ORIENTATION_ROTATE_180:
//                     degree = 180;
//                     break;
//                 case ExifInterface.ORIENTATION_ROTATE_270:
//                     degree = 270;
//                     break;
//             }
//         } catch (IOException e) {
//             e.printStackTrace();
//         }
//         return degree;
//     }
//
//     /**
//      * 生成二维码
//      *
//      * @param mContext         上下文
//      * @param name             文件名称
//      * @param url              生成二维码的内容
//      * @param smallerDimension 图片的宽度
//      * @return
//      */
//     public static Bitmap createQRCode(Context mContext, String name, String url, int smallerDimension) {
//         Intent intent = new Intent(Intents.Encode.ACTION);
//         intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
//         intent.putExtra(Intents.Encode.TYPE, Contents.Type.TEXT);
//         intent.putExtra(Intents.Encode.DATA, url);
//         intent.putExtra(Intents.Encode.FORMAT, BarcodeFormat.QR_CODE.toString());
//         FileOutputStream out = null;
//         Bitmap mBitmap = null;
//         File file = new File(mContext.getFilesDir(), name);
//         if (file.exists()) {
//             mBitmap = BitmapFactory.decodeFile(file.getPath());
//             return mBitmap;
//         }
//         // 字节输出流,文件输出流
//         ByteArrayOutputStream bos = new ByteArrayOutputStream();
//         try {
//             mBitmap = new QRCodeEncoder(mContext, intent, smallerDimension)
//                     .encodeAsBitmap();
//             mBitmap.compress(Bitmap.CompressFormat.PNG, 0, bos);
//             byte[] bitmapData = bos.toByteArray();
//             out = new FileOutputStream(file);
//             out.write(bitmapData);
//         } catch (Exception e) {
//             e.printStackTrace();
//         } finally {
//             try {
//                 bos.close();
//                 if (out != null) {
//                     out.close();
//                 }
//             } catch (IOException e) {
//                 e.printStackTrace();
//             }
//         }
//         return mBitmap;
//     }
//
//     public static File compressImage(File file) throws FileNotFoundException {
//         int degree = BitmapUtil.readPictureDegree(file.getAbsolutePath());
//         final BitmapFactory.Options options = new BitmapFactory.Options();
//         options.inJustDecodeBounds = true;
//
//         Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath(), options);
//         options.inSampleSize = BitmapUtil.calculateInSampleSize(options, 480, 800);
//         options.inJustDecodeBounds = false;
//         bitmap = BitmapFactory.decodeFile(file.getAbsolutePath(), options);
//         if (Math.abs(degree) > 0) {
//             bitmap = BitmapUtil.rotaingImageView(degree, bitmap);
//         }
//         logD(TAG, "bitmap.getByteCount = " + bitmap.getByteCount());
//         ByteArrayOutputStream baos = new ByteArrayOutputStream();
//         // 质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
//         bitmap.compress(Bitmap.CompressFormat.JPEG, 90, baos);
//         int quality = 90;
//         logD(TAG, "baos = " + baos.toByteArray().length);
//         // 循环判断如果压缩后图片是否大于1M,大于继续压缩
//         while (baos.toByteArray().length * 1.0 / 1024 / 1024 > 1) {
//             baos.reset();
//             logD(TAG, "quality = " + quality);
//             bitmap.compress(Bitmap.CompressFormat.JPEG, quality, baos);
//             logD(TAG, "baos = " + baos.toByteArray().length);
//             quality -= 10;
//             if (quality <= 10) {
//                 break;
//             }
//         }
//         File dir = new File(Environment.getExternalStorageDirectory() + "//EPA");
//         if (!dir.exists()) {
//             dir.mkdirs();
//         }
//         File outFile = new File(dir, file.getName());
//         // 把压缩后的数据存放到FileOutputStream中
//         try {
//             FileOutputStream fos = new FileOutputStream(outFile);
//             fos.write(baos.toByteArray());
//             fos.flush();
//             fos.close();
//         } catch (IOException e) {
//             e(TAG, "compressImage IOException = " + e.getMessage());
//         }
//         return outFile;
//     }
//
//     public static File compressImageForCrop(File file) throws FileNotFoundException {
//         int degree = BitmapUtil.readPictureDegree(file.getAbsolutePath());
//         final BitmapFactory.Options options = new BitmapFactory.Options();
//         options.inJustDecodeBounds = true;
//
//         Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath(), options);
//         options.inSampleSize = BitmapUtil.calculateInSampleSize(options, 1080, 1920);
//         options.inJustDecodeBounds = false;
//
//         bitmap = BitmapFactory.decodeFile(file.getAbsolutePath(), options);
//         if (Math.abs(degree) > 0) {
//             bitmap = BitmapUtil.rotaingImageView(degree, bitmap);
//         }
//
//         File dir = new File(Environment.getExternalStorageDirectory() + "//EPA");
//         if (!dir.exists()) {
//             dir.mkdirs();
//         }
//         File outFile = new File(dir, file.getName());
//         FileOutputStream fileOutputStream = new FileOutputStream(outFile);
//         bitmap.compress(Bitmap.CompressFormat.JPEG, 80, fileOutputStream);
//         bitmap.recycle();
//         bitmap = null;
//         try {
//             fileOutputStream.close();
//         } catch (IOException e) {
//             e(TAG, "compressImageForCrop IOException = " + e.getMessage());
//         }
//         return outFile;
//     }
//
//     /**
//      * 根据url生成黑色二维码
//      *
//      * @param mContext                上下文
//      * @param name                    文件名称
//      * @param urlRecommendImageView   生成二维码的内容
//      * @param recommendImageViewWidth 图片的宽度
//      * @return
//      */
//     public static Bitmap getRecommendBitmap(Context mContext, String name, String urlRecommendImageView, int recommendImageViewWidth) {
//         Bitmap mBitmap = null;
//         File fileRecommendImageView = new File(mContext.getFilesDir(), name);
//         FileOutputStream out = null;
//         if (fileRecommendImageView.exists()) {
//             mBitmap = BitmapFactory.decodeFile(fileRecommendImageView.getPath());
//         } else {
//             Intent intent = new Intent(Intents.Encode.ACTION);
//             intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
//             intent.putExtra(Intents.Encode.TYPE, Contents.Type.TEXT);
//             intent.putExtra(Intents.Encode.DATA, urlRecommendImageView);
//             intent.putExtra(Intents.Encode.FORMAT, BarcodeFormat.QR_CODE.toString());
//             mBitmap = CreateBitmapCode.createQRCodeBitmap(urlRecommendImageView, recommendImageViewWidth, recommendImageViewWidth);
//             try {
//                 // 字节输出流,文件输出流
//                 ByteArrayOutputStream bos = new ByteArrayOutputStream();
//                 try {
//                     mBitmap.compress(Bitmap.CompressFormat.PNG, 0, bos);
//                     byte[] bitmapData = bos.toByteArray();
//                     out = new FileOutputStream(fileRecommendImageView);
//                     out.write(bitmapData);
//                 } catch (Exception e) {
//                     e.printStackTrace();
//                 } finally {
//                     try {
//                         bos.close();
//                         if (out != null) {
//                             out.close();
//                         }
//                     } catch (IOException e) {
//                         e.printStackTrace();
//                     }
//                 }
//             } catch (Exception e) {
//
//             }
//         }
//         return mBitmap;
//     }
//
//     /**
//      * function  生成带头像的二维码
//      * @param src
//      * @param watermark
//      * @return
//      */
//     public static Bitmap createProcessBitmap(Bitmap src, Bitmap watermark) {
//         String tag = "createBitmap";
//         logD(tag, "create a new bitmap");
//         if (src == null) {
//             return null;
//         }
//         int w = src.getWidth();
//         int h = src.getHeight();
//         int ww = watermark.getWidth();
//         int wh = watermark.getHeight();
//         // create the new blank bitmap
//         Bitmap newb = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);// 创建一个新的和SRC长度宽度一样的位图
//         Canvas cv = new Canvas(newb);
//         final Paint paint = new Paint();
//         paint.setColor(ResUtil.getColor(R.color.color_3399ff));
//
//         // draw src into
//         cv.drawBitmap(src, 0, 0, paint);// 在 0，0坐标开始画入src
//
//         // 在src的中间画watermark
//         cv.drawBitmap(watermark, w / 2 - ww / 2, h / 2 - wh / 2, paint);// 设置ic_launcher的位置
//         // save all clip
//         cv.save(Canvas.ALL_SAVE_FLAG);// 保存
//         // store
//         cv.restore();// 存储
//         return newb;
//     }
//
//     /**
//      * function 生成二维码的头像进行缩小
//      * @param src
//      * @param destWidth
//      * @param destHeigth
//      * @return
//      */
//     public static Bitmap zoomBitmap(Bitmap src, int destWidth, int destHeigth) {
//         String tag = "lessenBitmap";
//         if (src == null) {
//             return null;
//         }
//         int w = src.getWidth();// 源文件的大小
//         int h = src.getHeight();
//         // calculate the scale - in this case = 0.4f
//         DisplayMetrics dm = EPApp.mContext.getResources().getDisplayMetrics();
// //        destWidth = (int) (destWidth * dm.density );
// //        destHeigth = (int) (destHeigth * dm.density);
//         float scaleWidth = ((float) destWidth) / w * (dm.density/3);// 宽度缩小比例
//         float scaleHeight = ((float) destHeigth) / h* (dm.density/3);// 高度缩小比例
//         Matrix m = new Matrix();// 矩阵
//         m.postScale(scaleWidth, scaleHeight);// 设置矩阵比例
//         Bitmap resizedBitmap = Bitmap.createBitmap(src, 0, 0, w, h, m, true);// 直接按照矩阵的比例把源文件画入进行
//         return resizedBitmap;
//     }
//
//     public static Bitmap getScaledBitmapFromFile(Context context, File file) {
//         DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
//         int targetW = displayMetrics.widthPixels;
//         int targetH = displayMetrics.heightPixels;
//
//         BitmapFactory.Options bmOptions = new BitmapFactory.Options();
//         // 该 值设为true那么将不返回实际的bitmap，也不给其分配内存空间这样就避免内存溢出了。但是允许我们查询图片的信息这其中就包括图片大小信息
//         bmOptions.inJustDecodeBounds = true;
//         //        // 如果inPurgeable设为True的话表示使用BitmapFactory创建的Bitmap,用于存储Pixel的内存空间在系统内存不足时可以被回收
// //        bmOptions.inPurgeable = true;
//         Bitmap bitmap;
//         BitmapFactory.decodeFile(file.getAbsolutePath(), bmOptions);
//         int photoW = bmOptions.outWidth;
//         int photoH = bmOptions.outHeight;
//         int scaleFactor = Math.min(photoW / targetW, photoH / targetH);
//         if (scaleFactor < 1) {
//             scaleFactor = 1;
//         }
//
//         // 设置恰当的inSampleSize可以使BitmapFactory分配更少的空间以消除该错误
//         bmOptions.inJustDecodeBounds = false;
//         bmOptions.inSampleSize = scaleFactor;
//         bitmap = BitmapFactory.decodeFile(file.getAbsolutePath(), bmOptions);
//         return bitmap;
//     }
//
//     public static byte[] convertFile2Bytes(File file) {
//         if (file == null) return null;
//         byte[] buffer = null;
//         try {
//             FileInputStream fis = new FileInputStream(file);
//             ByteArrayOutputStream bos = new ByteArrayOutputStream(1000);
//             byte[] b = new byte[1000];
//             int n;
//             while ((n = fis.read(b)) != -1) {
//                 bos.write(b, 0, n);
//             }
//             fis.close();
//             bos.close();
//             buffer = bos.toByteArray();
//         } catch (IOException e) {
//             e.printStackTrace();
//         }
//         return buffer;
//     }
//
//     /**
//      * 保存图片到相册
//      * @param mBitmap 图片
//      * @param name 存储图片名称
//      * @return 是否保存成功
//      */
//     public static boolean saveImgToAlbum(Bitmap mBitmap, String name) {
//         if (mBitmap == null || TextUtils.isEmpty(name)) {
//             return false;
//         }
//         boolean isSDexisting = Environment.getExternalStorageState().equals(
//                 Environment.MEDIA_MOUNTED);
//         boolean result = false;
//         if (!isSDexisting) {
//             ToastUtil.showToast("请确认SD卡已插入!");
//         } else {
//             File cameraFile = new File(Environment.getExternalStorageDirectory()
//                     + "//DCIM//Camera");
//             if(!cameraFile.exists()){
//                 cameraFile.mkdirs();
//             }
//             File imageFile = new File(Environment.getExternalStorageDirectory()
//                     + "//DCIM//Camera", name + ".jpg");
//             try {
//                 imageFile.createNewFile();
//                 FileOutputStream out = new FileOutputStream(imageFile);
//                 mBitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
//                 out.flush();
//                 out.close();
//                 result = true;
//             } catch (FileNotFoundException e) {
//                 e.printStackTrace();
//             } catch (IOException e) {
//                 e.printStackTrace();
//             }
//             MediaScannerConnection.scanFile(EPApp.mContext, new String[]{imageFile.getAbsolutePath()}, null, null);
//         }
//         return result;
//     }
//
//     /**
//      * 质量压缩方法
//      * @param image
//      * @return
//      */
//     public static Bitmap compressImage(Bitmap image) {
//         ByteArrayOutputStream baos = new ByteArrayOutputStream();
//         image.compress(Bitmap.CompressFormat.JPEG, 100, baos);// 质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
//         int options = 90;
//         while (baos.toByteArray().length / 1024 > 100) { // 循环判断如果压缩后图片是否大于100kb,大于继续压缩
//             baos.reset(); // 重置baos即清空baos
//             image.compress(Bitmap.CompressFormat.JPEG, options, baos);// 这里压缩options%，把压缩后的数据存放到baos中
//             options -= 10;// 每次都减少10
//         }
//         ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());// 把压缩后的数据baos存放到ByteArrayInputStream中
//         Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);// 把ByteArrayInputStream数据生成图片
//         return bitmap;
//     }
// }
