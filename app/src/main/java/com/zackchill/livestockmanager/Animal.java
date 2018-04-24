package com.zackchill.livestockmanager;

import java.io.Serializable;

/**
 * This class contains information for each animal.
 */
public class Animal implements Serializable
{
    private String animalId;
    private String animalType;
    private String animalName;
    private String animalGender;
    private String animalWeightKg;
    private String animalFertile;
    private String animalNeutered;
    private String animalBirthday;
    private String animalDeathday;

    public Animal(String animalId, String animalType, String animalName,
                  String animalGender, String animalWeightKg, String animalFertile,
                  String animalNeutered, String animalBirthday, String animalDeathday)
    {
        this.animalId = animalId;
        this.animalType = animalType;
        this.animalName = animalName;
        this.animalGender = animalGender;
        this.animalWeightKg = animalWeightKg;
        this.animalFertile = animalFertile;
        this.animalNeutered = animalNeutered;
        this.animalBirthday = animalBirthday;
        this.animalDeathday = animalDeathday;
    }

    public String getAnimalId() {
        return animalId;
    }

    public void setAnimalId(String animalId) {
        this.animalId = animalId;
    }

    public String getAnimalType() {
        return animalType;
    }

    public void setAnimalType(String animalType) {
        this.animalType = animalType;
    }

    public String getAnimalName() {
        return animalName;
    }

    public void setAnimalName(String animalName) {
        this.animalName = animalName;
    }

    public String getAnimalGender() {
        return animalGender;
    }

    public void setAnimalGender(String animalGender) {
        this.animalGender = animalGender;
    }

    public String getAnimalWeightKg() {
        return animalWeightKg;
    }

    public void setAnimalWeightKg(String animalWeightKg) {
        this.animalWeightKg = animalWeightKg;
    }

    public String getAnimalFertile() {
        return animalFertile;
    }

    public void setAnimalFertile(String animalFertile) {
        this.animalFertile = animalFertile;
    }

    public String getAnimalNeutered() {
        return animalNeutered;
    }

    public void setAnimalNeutered(String animalNeutered) {
        this.animalNeutered = animalNeutered;
    }

    public String getAnimalBirthday() {
        return animalBirthday;
    }

    public void setAnimalBirthday(String animalBirthday) {
        this.animalBirthday = animalBirthday;
    }

    public String getAnimalDeathday() {
        return animalDeathday;
    }

    public void setAnimalDeathday(String animalDeathday) {
        this.animalDeathday = animalDeathday;
    }
}
