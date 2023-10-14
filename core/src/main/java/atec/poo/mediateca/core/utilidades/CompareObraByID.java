package atec.poo.mediateca.core.utilidades;

import atec.poo.mediateca.core.Obra;

import java.util.Comparator;

public class CompareObraByID implements Comparator<Obra> {

    @Override
    public int compare(Obra o1, Obra o2) {
        return o1.getId() - o2.getId();
    }
}