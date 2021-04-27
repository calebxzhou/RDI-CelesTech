package cn.davickk.rdi.essentials.general.model;


public class HomeLocation extends Location{
    public final boolean isActive;
    public final String dims;
    public HomeLocation(String dims, double posX, double posY, double posZ, float rotationYaw, float rotationPitch, boolean isActive) {
        super(posX, posY, posZ, rotationYaw, rotationPitch,dims);
        this.isActive=isActive;
        this.dims=dims;
    }
}
