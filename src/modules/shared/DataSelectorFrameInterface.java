/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package modules.shared;

import com.github.lgooddatepicker.zinternaltools.Pair;
import java.util.ArrayList;

/**
 *
 * @author Azhar
 */
public interface DataSelectorFrameInterface<T> {
    public void dataSelectorOnDataSelected(T data);
    public Pair<ArrayList<T>, ArrayList<Object[]>> dataSelectorProvideData(String query);
}
