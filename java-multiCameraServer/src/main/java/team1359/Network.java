package team1359;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

public class Network{
    NetworkTableEntry xEntry;
    NetworkTableEntry distance;
    NetworkTableEntry angleToTarget;
    NetworkTableEntry frameSize;
    double x;
    double distanceFromTarget;
    double tempAngleValue;

    Calculation kCalculation;
    
    public Network(NetworkTableInstance existing){
        NetworkTableInstance inst = existing;
        NetworkTable xTable = inst.getTable("xTable");
        NetworkTable targetDistanceTable = inst.getTable("DistanceTable");
        NetworkTable sizeOfFrame = inst.getTable("sizeOfFrame");
        xEntry = xTable.getEntry("x");
        distance = targetDistanceTable.getEntry("distance");
        frameSize = sizeOfFrame.getEntry("frame");
        angleToTarget = xTable.getEntry("TAngle");
        //x = 0;
        //distanceFromTarget = 20;


        //tempAngleValue = 0;


        tempAngleValue = 0;
        Number[] frameDimentions = new Number[] {Calculation.getFrameWidth(), Calculation.getFrameHight()}; // {x, y}
        frameSize.setNumberArray(frameDimentions);
    }

    public void getTableValues(){
        double xValue = xEntry.getDouble(0);
        double distanceValue = distance.getDouble(0);
        System.out.println("--------------------" + xValue);
        System.out.println("====================" + distanceValue);
    }

    public void setTable(double xInput, double distanceInput, double angleInput){
        /*
        xEntry.setDouble(x);
        distance.setDouble(distanceFromTarget);
        if(x <= 1000){
            x += 1;
        }else{
            x = 0;
        }
        if(distanceFromTarget <= 1000){
            distanceFromTarget += 1;
        }else{
            distanceFromTarget = 0;
        }
        */
        // send X%, distance from target, angle from target
        xEntry.setDouble(xInput);
        distance.setDouble(distanceInput);
        angleToTarget.setDouble(angleInput);
    }
}