package com.smartlandapp.utility;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Point;
import com.tianditu.android.maps.GeoPoint;
import com.tianditu.android.maps.MapView;
import com.tianditu.android.maps.Overlay;
import com.tianditu.android.maps.Projection;
import java.util.ArrayList;

public class DrawLineUtil {

    private ArrayList<GeoPoint> mGeoPointList;
    private MapView mMapView;
    private Context mContext;

    public DrawLineUtil(Context context, ArrayList<GeoPoint> list,
                        MapView mapView) {
        mContext = context;
        mGeoPointList = list;
        mMapView = mapView;
        if(list != null && list.size() > 0){
            DrawOverlay mDrivingOverlay = new DrawOverlay(context);
            mMapView.getOverlays().add(mDrivingOverlay);
            mMapView.getController().animateTo(list.get(list.size() - 1));
            mMapView.postInvalidate();
        }
    }

    class DrawOverlay extends Overlay {

        public DrawOverlay(Context context) {

        }

        @Override
        public void draw(Canvas canvas, MapView mapView, boolean shadow) {
            super.draw(canvas, mapView, shadow);
            ArrayList<Point> PL = new ArrayList<Point>();
            Point[] pointScr = convertPoints(mapView.getProjection(),mGeoPointList);
            Paint paint = new Paint();
            paint.setAntiAlias(true);
            paint.setStrokeWidth(5);
            paint.setStyle(Style.STROKE);
            paint.setColor(Color.RED);
            Path path = new Path();
            path.moveTo(pointScr[0].x, pointScr[0].y);
            Point point = null;
            Projection projection = mapView.getProjection();
            for (int i = 1; i < pointScr.length; i++) {
                path.lineTo(pointScr[i].x, pointScr[i].y);
                point = new Point();
                projection.toPixels(mGeoPointList.get(i), point);
                PL.add(point);
            }
            canvas.drawPath(path, paint);
            path.reset();

//          Drawable mDrawableStart = (Drawable) mContext.getResources().getDrawable(R.mipmap.icon_route_start);
//          Drawable mDrawableEnd = (Drawable) mContext.getResources().getDrawable(R.mipmap.icon_route_end);

//          //起点
//          int wid = mDrawableStart.getIntrinsicWidth();
//          int height = mDrawableStart.getIntrinsicHeight();
//          if(PL != null && PL.size() > 0){
//              mDrawableStart.setBounds(PL.get(0).x - wid / 2, PL.get(0).y - height, PL.get(0).x + wid / 2,PL.get(0).y);
//              mDrawableStart.draw(canvas);
//          }
//
//          //终点
//          wid = mDrawableEnd.getIntrinsicWidth();
//          height = mDrawableEnd.getIntrinsicHeight();
//          if(PL != null && PL.size() > 0){
//              Point endPoint = PL.get(PL.size() - 1);
//              mDrawableEnd.setBounds(endPoint.x - wid / 2, endPoint.y - height, endPoint.x + wid / 2, endPoint.y);
//              mDrawableEnd.draw(canvas);
//          }
        }

        private Point[] convertPoints(Projection prj, ArrayList<GeoPoint> points) {
            Point[] ptsRet = new Point[points.size()];
            for (int i = 0; i < ptsRet.length; i++) {
                ptsRet[i] = prj.toPixels(points.get(i), null);
            }
            return ptsRet;
        }
    }
}
