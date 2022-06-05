package io.prbaa.enumeration;

public enum Status {

    STATUS_UP("status_up"),STATUS_DOWN("status_down");
    private String status;
    Status(String status){
        this.status=status;
    }

    public String getStatus(){
        return this.status=status;
    }
}
