package me.uwu.dont.cap.struct;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

@SuppressWarnings("SpellCheckingInspection")
public @Data class Capayload {
    private static final Gson gson = new Gson();
    private final String username, password;
    private final String proxy;
    @SerializedName("proxytype")
    private final String proxyType;
    @SerializedName("sitekey")
    private final String siteKey;
    private final String host;

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
    //aled


    @Override
    public String toString() {
        return gson.toJson(this);
    }

    public void setOptions(Options options) {
        requestData = options.getRequestData();
        sleep = options.getSleep();
        values1 = options.getValues1();
        values2 = options.getValues2();
        delay = options.getDelay();
        delayRandomization = options.getDelayRandomization();
        unitSpeed = options.getUnitSpeed();
        unitSpeedRandomization = options.getUnitSpeedRandomization();
        unitSpeedAngleDeltaWeight = options.getUnitSpeedAngleDeltaWeight();
        unitSpeedAngleDeltaBias = options.getUnitSpeedAngleDeltaBias();
        directionChangeAngle = options.getDirectionChangeAngle();
        directionChangeAmplitude = options.getDirectionChangeAmplitude();
        edgeHold = options.getEdgeHold();
        holdRandomization = options.getHoldRandomization();
        idleAngle = options.getIdleAngle();
    }
}
