package team1359;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

public class Network{
    NetworkTableEntry xEntry;
    NetworkTableEntry distance;
    double x;
    double distanceFromTarget;
    
    public Network(){
        NetworkTableInstance inst = NetworkTableInstance.getDefault();
        NetworkTable xTable = inst.getTable("xTable");
        NetworkTable targetDistanceTable = inst.getTable("DistanceTable");
        xEntry = xTable.getEntry("x");
        distance = targetDistanceTable.getEntry("distance");
        x = 0;
        distanceFromTarget = 20;
    }

    public void getTableValues(){
        double xValue = xEntry.getDouble(0);
        double distanceValue = distance.getDouble(0);
        System.out.println("--------------------" + xValue);
        System.out.println("====================" + distanceValue);
    }

    public void setTable(){
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
    }
}