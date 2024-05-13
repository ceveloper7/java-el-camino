package com.ceva.section5.poo;

/**
 * Diferencia entre una clase Abstract y una Interface
 * 1. No se puede crear una instancia de una Interface ni de una clase Abstract
 * 2. En la clase abstract se puede definir campos que no son static ni final
 * 3. En la clase abstract se pueden definir metodos public, private, protected, con la interface
 *    los campos son public static final y todos los metodos son public
 * 4. Una clase solo puede extends a otra clase mientras que una clase puede implementar
 *    varias interface
 * Nota
 *    Se considera emplear un abstract class cuando queremos desarrollar una funcionalidad comun
 *    para clases muy similares o cuando necesitemos implementar campos o metodos private
 *    Se considera emplear una inaterface cuando sea utilizada por muchas clases que no estan
 *    relacionadas entre si o cuando queremos crear un comportamiento sin importar su implementacion
 */
public abstract class LCatalog {
    public void list(){
        // mostrar lista de registros en pantalla
    }

    public void search(String name){
        // buscar un registro y mostrar el resultado en pantalla
    }

    public abstract void load();
    public abstract void save();

}
