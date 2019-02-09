package team1359;

import team1359.Main.MyPipeline;
import team1359.Network;
import java.util.ArrayList;
import java.util.Collections;

import org.opencv.core.*;
import org.opencv.imgproc.*;

public class Calculation{

    public static final float ReflectorAreaAtOneFoot = 100;

    // float averageCenterX;
    // float largestArea;
    // float largestWidth;
    // int targetCount;
    // float averageDist;
    // float angleToTarget;

    MyPipeline pipeline;
    Network knetwork;

    // float distConvertion = 1280;
    // int screenWidth = 640;
    // int screenCenter = 320;

    public Calculation(){

    }

    public void processContours(ArrayList<MatOfPoint> contours){
        ArrayList<RotatedRect> RotRectContours = getMinBoundingRects(contours);
        Collections.sort(RotRectContours, new ContourPosComparator());

    }

    public ArrayList<RotatedRect> getMinBoundingRects(ArrayList<MatOfPoint> input){
        ArrayList<RotatedRect> tempContours = new ArrayList<RotatedRect>();
        for (MatOfPoint m : input) {
            MatOfPoint2f cont = new MatOfPoint2f(m);
            tempContours.add(Imgproc.minAreaRect(cont));
        }

        return tempContours;
    }


    public float findTargetAngle(){

        return 1;
    }

    public int getCenterOfTarget(ArrayList<RotatedRect> contours){
        int correctIndex = -1;
        for(int i = 0; i < (contours.size() - 1); i++){
            if(contours.get(i).angle > 0 && contours.get(i+1).angle < 0){
                correctIndex = i;
            }
        }
        // what do we return if correctIndex is not present
        return (contours.get(correctIndex+1).boundingRect().x + contours.get(correctIndex).boundingRect().x)/2;
    }


    public float getDistanceToHatch(){

        return 0; //distance to hatch from robot
    }

    public MatOfPoint[] getSortedContours(MatOfPoint[] unsorted){
        return null;
    }

    public float getAngleFromHatch(){
        return 0; //angle hatch is at from perpendicular
    }

    public float getCenterOfHatch(){
        return 0; //precentage center of hatch is off from center screen
    }

    // public void VisionTargets(){
    //     float sumCenterX = 0;
    //     ArrayList<MatOfPoint> totalContours = pipeline.findContoursOutput();
    //     targetCount = 0;
    //     largestArea = 0;
    //     largestWidth = 0;
        

    //     for(MatOfPoint m : totalContours){
    //         largestArea = Math.max(largestArea, (float)Imgproc.contourArea(m));
    //         Rect contourRect = Imgproc.boundingRect(m);
    //         largestWidth = Math.max(largestWidth, (float)contourRect.width);
    //         averageCenterX += sumCenterX + contourRect.x + contourRect.width / 2;
    //         targetCount++;
    //     };

    //     if(targetCount > 0){
    //         averageCenterX = sumCenterX / targetCount;
    //         angleToTarget = getAngleToTarget(averageCenterX, largestWidth);
    //     }else{
    //         angleToTarget = 0;
    //     };

    // }

    // public float getAverageCenterx(){
    //     return averageCenterX;
    // }

    // public float getLargestArea(){
    //     return 1;
    // }

    // public float getAngleToTarget(float targetCenterx, float targetWidth){
    //     if(targetWidth != 0){
    //         float distanceFromTarget = distConvertion / targetWidth;
    //         float centerToTarget = distanceFromTarget * (targetCenterx - screenCenter) / screenWidth;
    //         return (float)(Math.atan2(centerToTarget, distanceFromTarget) * 180 / Math.PI);
    //     }else{
    //         return 0;
    //     }
    // }

    // public int getTargetNumber(){
    //     return 1;
    // }

    // public void updateTable(){
    //     knetwork.setTable(averageCenterX, averageDist, angleToTarget);
    // }
}