package Interface;

import AbstractClass.Plant;
import Exception.IndexOutOfRangeException;
import Exception.InvalidInventorySwapException;
import Exception.RemoveNullException;
import Exception.InvalidStoringException;
import Exception.InvalidPlacingToTileException;
import Exception.InvalidDeckSwapException;

public interface Stockable {
    public void addItem(Plant plant) throws InvalidStoringException;
    public void removeItem(Plant plant)throws RemoveNullException;
    public void switchPlacement(int index1, int index2)throws InvalidInventorySwapException, IndexOutOfRangeException;
}
