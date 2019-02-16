package team1359;

import team1359.Main.MyPipeline;
import team1359.Network;
import java.util.ArrayList;
import java.util.Collections;

import org.opencv.core.*;
import org.opencv.imgproc.*;

public class Calculation{


    // float averageCenterX;
    // float largestArea;
    // float largestWidth;
    // int targetCount;
    // float averageDist;
    // float angleToTarget;
    public static final float ReflectorAreaAtOneFoot = 100;

    MyPipeline pipeline;
    Network knetwork;

    static int frameWidth = 0;
    static int frameHeight = 0;
    private double xTemp;
    private double areaOfTarget;
    private int centerOfTarget = -1;
   // private double leftContourLength;
    //private double rightContourLength;
   // private double angleRatio;
    private double lengthOfTarget;
    private double angleToTargetRatio = 8.0/45.0;
   // private boolean initialArea = true;
    private final double areaFromMaxDistance = 5; // change
   // private float withinAngleToTarget = .9f; // change
    //double initialAreaOfTarget;

    // float distConvertion = 1280;
    // int screenWidth = 640;
    // int screenCenter = 320;

    // float averageCenterX;
    // float largestArea;
    // float largestWidth;
    // int targetCount;
    // float averageDist;
    // float angleToTarget;

    public Calculation(){

    }

    public void processContours(ArrayList<MatOfPoint> contours){
        if(contours != null){
            if(contours.size() > 0){
                frameWidth = (int)contours.get(0).size().width;
                frameHeight = (int)contours.get(0).size().height;
            }
        }
        
        
        ArrayList<RotatedRect> RotRectContours = getMinBoundingRects(contours);
        Collections.sort(RotRectContours, new ContourPosComparator());
        findTarget(RotRectContours);
        //getCenterOfTarget(RotRectContours);
        xTemp = (double)((getCenterOfTarget()/getFrameWidth())*100); // setting it to a percentage
    }

    private ArrayList<RotatedRect> getMinBoundingRects(ArrayList<MatOfPoint> input){
        ArrayList<RotatedRect> tempContours = new ArrayList<RotatedRect>();
        for (MatOfPoint m : input) {
            MatOfPoint2f cont = new MatOfPoint2f(m);
            tempContours.add(Imgproc.minAreaRect(cont));
        }

        return tempContours;
    }


    public double getAngleFromTarget(){ // doesnt need to be double. dont know how to work around networkTable
        // angleRatio = (leftContourLength/rightContourLength)*getDistanceFromTarget();
        // if(angleRatio <= .5){ // big angle to the left
        //     return -2;
        // }
        // else if(angleRatio > .5 && angleRatio < .9){ //small angle to the left
        //     return -1;
        // }
        // else if(angleRatio >= 2){ // big angle to the right
        //     return 2;
        // }
        // else if(angleRatio < 2 && angleRatio > 1.1){ // small angle to the right
        //     return 1;
        // }
        // else{ // within angleToTarget
        //     return 0;
        // }
        //
        return (lengthOfTarget-1)/(angleToTargetRatio*(1/(getDistanceFromTarget())));
    }

    public double getDistanceFromTarget(){
       // ratio = initialAreaOfTarget/constant; // calculation to find ratio. can be hard coded
        return Math.sqrt(areaFromMaxDistance/areaOfTarget);
    }

    public int getCenterOfTarget(){
       return centerOfTarget;
    }

    public void findTarget(ArrayList<RotatedRect> contours){
        int correctIndex = -1;
        for(int i = 0; i < (contours.size() - 1); i++){
            if(contours.get(i).angle > 0 && contours.get(i+1).angle < 0){
                correctIndex = i;
            }
        }
        if(correctIndex > -1){
            // if(initialArea){
            //     initialAreaOfTarget = (contours.get(correctIndex).size.area() + contours.get(correctIndex+1).size.area())/2;
            //     initialArea = false;
            // }
           //leftContourLength = Math.sqrt(contours.get(correctIndex).size.area());
            //rightContourLength = Math.sqrt(contours.get(correctIndex+1).size.area());
            lengthOfTarget = (contours.get(correctIndex).size.width + contours.get(correctIndex + 1).size.width)/2;
            areaOfTarget = ((contours.get(correctIndex).size.area()) +(contours.get(correctIndex+1).size.area()))/2;
            centerOfTarget = (int)((contours.get(correctIndex+1).center.x + contours.get(correctIndex).center.x)/2);
        }
        else{
        }
    }

    public double getXValue(){
        return xTemp;
    }

    public static int getFrameWidth(){
        return frameWidth;
    }

    public static int getFrameHight(){
        return frameHeight;
    }

    public MatOfPoint[] getSortedContours(MatOfPoint[] unsorted){
        return null;
    }

    // public float getAngleFromHatch(){
    //     return 0; //angle hatch is at from perpendicular
    // }

    // public double getPercentageToTarget(){
    //     return (double)(getCenterOfTarget(RotRectContours)/getFrameWidth());
    // }

    // public float getCenterOfHatch(){
    //     return 0; //precentage center of hatch is off from center screen
    // }

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