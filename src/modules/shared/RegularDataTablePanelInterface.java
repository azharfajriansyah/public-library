/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modules.shared;

import java.util.ArrayList;

/**
 *
 * @author Azhar
 */
public interface RegularDataTablePanelInterface {
    public void onEditClicked(int row);
    public void onDeleteClicked(int row);
    public void onPrintClicked();
    public ArrayList<Object[]> provideData(String query);
}
