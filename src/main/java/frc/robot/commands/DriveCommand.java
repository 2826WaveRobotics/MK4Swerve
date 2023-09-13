package frc.robot.commands;

import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveSubsystem;

import java.util.function.DoubleSupplier;

public class DriveCommand extends CommandBase {
    private final DriveSubsystem m_driveSubsystem;

    private Double m_translationXSupplier = 0.0;
    private Double m_translationYSupplier = 0.0;
    private Double m_rotationSupplier = 0.0;

    public DriveCommand(DriveSubsystem driveSubsystem,
                               Double translationXSupplier,
                               Double translationYSupplier,
                               Double rotationSupplier) {
        this.m_driveSubsystem = driveSubsystem;
        this.m_translationXSupplier = translationXSupplier;
        this.m_translationYSupplier = translationYSupplier;
        this.m_rotationSupplier = rotationSupplier;

        addRequirements(driveSubsystem);
    }

    @Override
    public void execute() {
        // You can use `new ChassisSpeeds(...)` for robot-oriented movement instead of field-oriented movement
        m_driveSubsystem.drive(
                ChassisSpeeds.fromFieldRelativeSpeeds(
                        m_translationXSupplier,
                        m_translationYSupplier,
                        m_rotationSupplier,
                        m_driveSubsystem.getGyroscopeRotation()
                )
        );
    }

    @Override
    public void end(boolean interrupted) {
        m_driveSubsystem.drive(new ChassisSpeeds(0.0, 0.0, 0.0));
    }
}
