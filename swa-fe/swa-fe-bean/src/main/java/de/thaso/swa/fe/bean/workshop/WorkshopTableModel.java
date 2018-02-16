package de.thaso.swa.fe.bean.workshop;

import javax.enterprise.inject.Any;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Any
public class WorkshopTableModel implements Serializable {

    private List<WorkshopModel> workshopModelList = new ArrayList<>();

    public List<WorkshopModel> getWorkshopModelList() {
        return workshopModelList;
    }

    public void setWorkshopModelList(List<WorkshopModel> workshopModelList) {
        this.workshopModelList = workshopModelList;
    }

    public void addWorkshop(final WorkshopModel workshopModel) {
        workshopModelList.add(workshopModel);
    }
}
