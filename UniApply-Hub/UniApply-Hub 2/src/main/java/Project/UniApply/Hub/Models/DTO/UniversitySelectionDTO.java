package Project.UniApply.Hub.Models.DTO;

import java.util.List;

public class UniversitySelectionDTO {
    private List<Integer> selectedUniversitiesIds;

    public UniversitySelectionDTO() {
    }

    public UniversitySelectionDTO(List<Integer> selectedUniversitiesIds) {
        this.selectedUniversitiesIds = selectedUniversitiesIds;
    }

    public List<Integer> getSelectedUniversitiesIds() {
        return selectedUniversitiesIds;
    }

    public void setSelectedUniversitiesIds(List<Integer> selectedUniversitiesIds) {
        this.selectedUniversitiesIds = selectedUniversitiesIds;
    }
}

