package encityproject.rightcodeit.com.encityproject.ui.busTracker.model;

import java.io.Serializable;

public class Mqttbusenter
{
    private String loginbus;
    private String passbus;
    private String serverbus;
    private String topicbus;

    public Mqttbusenter(String loginbus, String passbus, String serverbus,
                        String topicbus)
    {
        super();
        this.loginbus = loginbus;
        this.passbus = passbus;
        this.serverbus = serverbus;
        this.topicbus = topicbus;
    }
    public String getLoginbus()
    {
        return loginbus;
    }
    public void setLoginbus(String loginbus)
    {
        this.loginbus = loginbus;
    }
    public String getPassbus()
    {
        return passbus;
    }
    public void setPassbus(String passbus)
    {
        this.passbus = passbus;
    }
    public String getServerbus()
    {
        return serverbus;
    }
    public void setServerbus(String serverbus)
    {
        this.serverbus = serverbus;
    }
    public String getTopicbus()
    {
        return topicbus;
    }
    public void setTopicbus(String topicbus)
    {
        this.topicbus = topicbus;
    }


}
