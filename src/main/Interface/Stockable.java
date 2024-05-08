package Interface;

import AbstractClass.Plant;

public interface Stockable {
    public void addItem(Plant plant);
    public void removeItem(Plant plant);
    public void switchPlacement(int index1, int index2);
}
