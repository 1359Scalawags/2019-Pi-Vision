package team1359;

import team1359.Main.MyPipeline;
import team1359.Network;
import java.util.ArrayList;
import org.opencv.core.*;
import org.opencv.imgproc.*;

public class Calculation{

    float averageCenterX;
    float largestArea;
    float largestWidth;
    int targetCount;
    float averageDist;
    float angleToTarget;

    MyPipeline pipeline;
    Network knetwork;

    float distConvertion = 1280;
    int screenWidth = 640;
    int screenCenter = 320;

    public Calculation(){

    }

    public void VisionTargets(){
        float sumCenterX = 0;
        ArrayList<MatOfPoint> totalContours = pipeline.findContoursOutput();
        targetCount = 0;
        largestArea = 0;
        largestWidth = 0;
        

        for(MatOfPoint m : totalContours){
            largestArea = Math.max(largestArea, (float)Imgproc.contourArea(m));
            Rect contourRect = Imgproc.boundingRect(m);
            largestWidth = Math.max(largestWidth, (float)contourRect.width);
            averageCenterX += sumCenterX + contourRect.x + contourRect.width / 2;
            targetCount++;
        };

        if(targetCount > 0){
            averageCenterX = sumCenterX / targetCount;
            angleToTarget = getAngleToTarget(averageCenterX, largestWidth);
        }else{
            angleToTarget = 0;
        };

    }

    public int getCenterOfTarget(ArrayList<RotatedRect> contours){
        int correctIndex = -1;
        for(int i = 0; i < (contours.size() - 1); i++){
            if(contours.get(i).angle > 0 && contours.get(i+1).angle < 0){
                correctIndex = i;
            }
        }
        return 0;
    }

    public float getAverageCenterx(){
        return averageCenterX;
    }

    public float getLargestArea(){
        return 1;
    }

    public float getAngleToTarget(float targetCenterx, float targetWidth){
        if(targetWidth != 0){
            float distanceFromTarget = distConvertion / targetWidth;
            float centerToTarget = distanceFromTarget * (targetCenterx - screenCenter) / screenWidth;
            return (float)(Math.atan2(centerToTarget, distanceFromTarget) * 180 / Math.PI);
        }else{
            return 0;
        }
    }

    public int getTargetNumber(){
        return 1;
    }

    public void updateTable(){
        knetwork.setTable(averageCenterX, averageDist, angleToTarget);
    }
}