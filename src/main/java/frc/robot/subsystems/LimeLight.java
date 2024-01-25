// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.LimeLightHelper;

public class LimeLight extends SubsystemBase {
  /** Creates a new Limelight. */
  public boolean isCenterd = false;
  public boolean isLeft = false;
  public boolean isRight = false;
  public LimeLight() {
    NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
    NetworkTableEntry tx = table.getEntry("tx");
    NetworkTableEntry ty = table.getEntry("ty");
    NetworkTableEntry ta = table.getEntry("ta");

//read values periodically


//post to smart dashboard periodically

  }
  private  double getTheObject(){
    return LimeLightHelper.getTX("limelight");
  }
  public boolean isCenterd(){
     if (getTheObject() > -4.0 && getTheObject()< 4.0) {
        return true;
    }else{
      return false;
    }
  }
  public boolean isRight(){
    if (getTheObject() > 4.0) {
        return true;
    }else{
      return false;
    }
  }
  public boolean isLeft(){
    if (getTheObject() < -4.0 ) {
        return true;
    }else{
      return false;
    }
  }
  public void directionOfTheObject(){
    if (getTheObject() > -4.0 && getTheObject()< 4.0) {
        isCenterd = true;
        isLeft = false;
        isRight = false;

    }else if (getTheObject() < -4.0 ) {
        isLeft = true;
        isCenterd = false;
        isRight = false;
    } else if (getTheObject() > 4.0) {
        isLeft = false;
        isCenterd = false;
        isRight = true;
    }
}


  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    getTheObject();
    directionOfTheObject();
     SmartDashboard.putBoolean("IsCenterd", isCenterd);
     SmartDashboard.putBoolean("IsRight", isRight);
     SmartDashboard.putBoolean("IsLeft", isLeft);
     SmartDashboard.putNumber("TX", LimeLightHelper.getTX("limelight"));
     SmartDashboard.putNumber("TY", LimeLightHelper.getTY("limelight"));
     SmartDashboard.putNumber("TA", LimeLightHelper.getTA("limelight"));

  }
}