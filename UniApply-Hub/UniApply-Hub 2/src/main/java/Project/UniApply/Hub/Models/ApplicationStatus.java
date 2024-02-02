package Project.UniApply.Hub.Models;

import javax.persistence.Entity;

@Entity
public class AplicationStatus {


    public enum ApplicationStatusType {
        PENDING,
        APPROVED,
        DENIED
    }



}
