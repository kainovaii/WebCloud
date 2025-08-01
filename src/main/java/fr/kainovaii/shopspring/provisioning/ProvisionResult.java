package fr.kainovaii.shopspring.provisioning;

public class ProvisionResult
{
    private final String ipAddress;
    private final String hostname;

    public ProvisionResult(String ipAddress, String hostname)
    {
        this.ipAddress = ipAddress;
        this.hostname = hostname;
    }

    public String getIpAddress() { return ipAddress; }

    public String getHostname() { return hostname; }
}
