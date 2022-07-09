package me.uwu.dont.cap.struct;

import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Data;

@Builder
public @Data class Options {
    @SuppressWarnings("SpellCheckingInspection")
    @SerializedName("rqdata")
    private String requestData;
    @SerializedName("SLEEP")
    private Integer sleep;
    @SerializedName("VALUES_1")
    private Integer values1;
    @SerializedName("VALUES_2")
    private Integer values2;
    @SerializedName("DELAY")
    private Integer delay;
    @SerializedName("DELAY_RND")
    private Integer delayRandomization;
    @SerializedName("UNIT_SPEED")
    private Integer unitSpeed;
    @SerializedName("UNIT_SPEED_RND")
    private Integer unitSpeedRandomization;
    @SerializedName("UNIT_SPEED_ANGLE_DELTA_WEIGHT")
    private Integer unitSpeedAngleDeltaWeight;
    @SerializedName("UNIT_SPEED_ANGLE_DELTA_BIAS")
    private Integer unitSpeedAngleDeltaBias;
    @SerializedName("DIR_CHANGE_ANGLE")
    private Integer directionChangeAngle;
    @SerializedName("DIR_CHANGE_AMPL")
    private Integer directionChangeAmplitude;
    @SerializedName("EDGE_HOLD")
    private Integer edgeHold;
    @SerializedName("HOLD_RND")
    private Integer holdRandomization;
    @SerializedName("IDLE_ANGLE")
    private Integer idleAngle;
}
