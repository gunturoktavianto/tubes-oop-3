package Interface;

import AbstractClass.Plant;
import Exception.IndexOutOfRangeException;
import Exception.InvalidInventorySwapException;
import Exception.RemoveNullException;

public interface Stockable {
    public void addItem(Plant plant);
    public void removeItem(Plant plant)throws RemoveNullException;
    public void switchPlacement(int index1, int index2)throws InvalidInventorySwapException, IndexOutOfRangeException;
}
