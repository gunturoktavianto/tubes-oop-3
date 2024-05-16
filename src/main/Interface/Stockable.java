package main.Interface;

import main.AbstractClass.Plant;
import main.Exception.IndexOutOfRangeException;
import main.Exception.InvalidInventorySwapException;
import main.Exception.RemoveNullException;
import main.Exception.InvalidStoringException;
import main.Exception.InvalidPlacingToTileException;
import main.Exception.InvalidDeckSwapException;

public interface Stockable {
    public void addItem(Plant plant) throws InvalidStoringException;
    public void removeItem(Plant plant)throws RemoveNullException;
    public void switchPlacement(int index1, int index2)throws InvalidInventorySwapException, IndexOutOfRangeException;
}
