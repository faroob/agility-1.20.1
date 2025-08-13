package net.faroob.agility;


public interface DataAccessor {



    float getAgilityYaw();
    void setAgilityYaw(float yaw);

    float getAgilityPitch();
    void setAgilityPitch(float pitch);

    boolean isSliding();
    void setSliding(boolean setSliding);

    boolean isSlamming();
    void setSlamming(boolean setSlamming);

    float getStamina();
    void setSliding(float stamina);

    boolean getAgilityDashCooldown();
    void setDashCooldown(boolean dashCooldown);

    float getSlamCounter();
    void setSlamCounter(float setSlamCounter);
}
