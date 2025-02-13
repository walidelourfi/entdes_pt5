package org.entdes;

import org.entdes.todolist.Tasca;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TascaTest {
    Tasca t;

    @BeforeEach
    void SetUp() throws Exception{

        t = new Tasca("tasca");
    }

    @Test
    void obtindreEsTascaCompletadaSenseCanvis(){
        assertFalse(t.isCompletada());
    }

    @Test
    void ficarTascaCompletada(){
        t.setCompletada(false);
        t.setCompletada(true);
        assertTrue(t.isCompletada());
    }

    @Test
    void ficarTascaNoCompletada(){
        t.setCompletada(true);
        t.setCompletada(false);
        assertFalse(t.isCompletada());
    }

    @Test
    void obtindreDescripcio(){
        assertEquals("tasca", t.getDescripcio());
    }

    @Test
    void ficarNovaDescrpicio(){
        t.setDescripcio("nova descripcio");
        assertEquals("nova descripcio", t.getDescripcio());
    }

    @Test
    void ficarNovaDescripcioBuida(){
        t.setDescripcio("");
        assertEquals("", t.getDescripcio());
        
    }

    @Test
    void ficarNovaDescripcioNull(){
        t.setDescripcio(null);
        assertNull(t.getDescripcio());
    }

    @Test
    void obtindreDataInici(){
        assertNull(t.getDataInici());
    }

    @Test
    void obtindreDataPrevistaFi(){
        assertNull(t.getDataFiPrevista());
    }

    @Test
    void ficarNovaDataInici(){
        t.setDataInici(LocalDate.of(2006, 1, 3));
        assertEquals(LocalDate.of(2006, 1, 3), t.getDataInici());
    }

    @Test
    void ficarNovaDataPrevistaFi(){
        t.setDataFiPrevista(LocalDate.of(2026,1,1));
        assertEquals(LocalDate.of(2026,1,1), t.getDataFiPrevista());
    }

    @Test
    void obtindrePrioritat(){
        assertNull(t.getPrioritat());
    }

    @Test
    void ficarNovaPrioritat(){
        t.setPrioritat(10);
        assertEquals(10, t.getPrioritat());;
    }

    @Test
    void ficarDataFiReal(){
        t.setDataFiReal(LocalDate.now());
        assertEquals(LocalDate.now(), t.getDataFiReal());
    }

    @Test
    void obtindreToStringSenseCompletar(){
        assertEquals("tasca: Pendent", t.toString());
    }

    @Test
    void obtindreToStringCompletat(){
        t.setCompletada(true);
        assertEquals("tasca: Completada", t.toString());
    }

}