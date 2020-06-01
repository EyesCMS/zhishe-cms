package edu.fzu.zhishe.core.dto;

import java.util.List;

/**
 * @author PSF(52260506 @ qq.com)
 * @
 */
public class SysAdminClubSpeciesDTO {

    private List<String> clubSpecies;
    private List<Integer> clubSpeciesNumber;

    public List<String> getClubSpecies() {
        return clubSpecies;
    }

    public void setClubSpecies(List<String> clubSpecies) {
        this.clubSpecies = clubSpecies;
    }

    public List<Integer> getClubSpeciesNumber() {
        return clubSpeciesNumber;
    }

    public void setClubSpeciesNumber(List<Integer> clubSpeciesNumber) {
        this.clubSpeciesNumber = clubSpeciesNumber;
    }
}
