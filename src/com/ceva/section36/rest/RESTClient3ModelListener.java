package com.ceva.section36.rest;

/**
 * Interface que representa los eventos en el modelo
 */
public interface RESTClient3ModelListener {
    // evento q se llama cuando se inserta un registro
    public void dataInserted(ProductData data, int row);
    public void dataUpdated(ProductData data, int row);
    public void dataDeleted(ProductData data, int row);
    // evento q se llama cuando cambiaron todos los datos
    public void dataChanged();
}
