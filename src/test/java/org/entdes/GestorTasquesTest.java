package org.entdes;

import org.entdes.todolist.GestorTasques;
import org.entdes.todolist.Tasca;
import org.entdes.mail.IEmailService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Value;

import java.beans.Transient;
import java.time.LocalDate;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class GestorTasquesTest {

    private GestorTasques gestor;
    private IEmailService emailService;

    @BeforeEach
    void setUp(){
        gestor = new GestorTasques();
    }

    @Test
    void afegirPrimeraTasca() throws Exception{
        int id = gestor.afegirTasca("nova tasca", LocalDate.now(), LocalDate.now().plusDays(1), 1);
        Tasca t = gestor.obtenirTasca(id);
        assertEquals("nova tasca",t.getDescripcio());
    }

    @Test
    void afegirTascaBuida(){
        try{
            gestor.afegirTasca("", null, null, null);
        } catch (Exception e){
            assertEquals("La descripció no pot estar buida.", e.getMessage());
        }
    }

    @Test
    void afegirTascaNull(){
        try{
            gestor.afegirTasca(null, null, null, null);
        } catch (Exception e){
            assertEquals("La descripció no pot estar buida.", e.getMessage());
        }
    }

    @Test
    void afegirTascaSenseDatesNiPrioritat() throws Exception{
        int id = gestor.afegirTasca("sense dates", null, null, null);
        Tasca t = gestor.obtenirTasca(id);
        assertNull(t.getDataInici());
        assertNull(t.getDataFiPrevista());
        assertNull(t.getPrioritat());;
    }

    @Test
    void afegirTascaAmbMateixaDescripcio() throws Exception{
        gestor.afegirTasca("tasca", null, null, null);
        try{
            gestor.afegirTasca("tasca", null, null, null);
        } catch (Exception e){
            assertEquals("Ja existeix una tasca amb la mateixa descripció", e.getMessage());
        }
    }

    @Test
    void afegirTascaAmbMateixaDescripcioMajuscules() throws Exception{
        gestor.afegirTasca("tasca", null, null, null);
        try{
            gestor.afegirTasca("TASCA", null, null, null);
        } catch (Exception e){
            assertEquals("Ja existeix una tasca amb la mateixa descripció", e.getMessage());
        }
    }

    @Test
    void llistarTasquesGeneral() throws Exception{
        gestor.afegirTasca("tasca1", null, null, null);
        gestor.afegirTasca("tasca2", null, null, null);
        gestor.afegirTasca("tasca3", null, null, null);
        List<Tasca> tasques = gestor.llistarTasques();

        assertEquals("tasca1", tasques.get(0).getDescripcio());
        assertEquals("tasca2", tasques.get(1).getDescripcio());
        assertEquals("tasca3", tasques.get(2).getDescripcio());
    }

    @Test
    void llistarTasquesGeneralSenseTasques(){
        List<Tasca> tasques = gestor.llistarTasques();
        assertEquals(0, tasques.size());
    }

    @Test
    void llistarTasquesPerCompletacioFalsa() throws Exception{
        Tasca t1 = gestor.obtenirTasca(gestor.afegirTasca("tasca1", null, null, null));
        Tasca t2 = gestor.obtenirTasca(gestor.afegirTasca("tasca2", null, null, null));
        Tasca t3 = gestor.obtenirTasca(gestor.afegirTasca("tasca3", null, null, null));

        t1.setCompletada(true);
        t2.setCompletada(true);
        t3.setCompletada(false);

        List<Tasca> tasques = gestor.llistarTasquesPerComplecio(false);

        assertEquals("tasca3", tasques.get(0).getDescripcio());
    }
}