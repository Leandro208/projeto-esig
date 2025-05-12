package io.github.Leandro208.projetoESIG.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.lang.reflect.Field;
import java.util.Collections;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import io.github.Leandro208.projetoESIG.dominio.HistoricoCalculoSalario;
import io.github.Leandro208.projetoESIG.dominio.PessoaSalarioConsolidado;
import io.github.Leandro208.projetoESIG.service.PessoaSalarioConsolidadoService;

public class PessoaSalarioConsolidadoMBeanTest {
	
	  @InjectMocks
	    private PessoaSalarioConsolidadoMBean mBean;

	    @Mock
	    private PessoaSalarioConsolidadoService serviceMock;

	    @BeforeEach
	    void setup() throws Exception {
	        MockitoAnnotations.openMocks(this);

	        
	        Field serviceField = PessoaSalarioConsolidadoMBean.class.getDeclaredField("service");
	        serviceField.setAccessible(true);
	        serviceField.set(mBean, serviceMock);
	    }

	    @Test
	    void deveCalcularSalarioComSucesso() throws InterruptedException {
	       
	        when(serviceMock.consultar(any()))
	                .thenReturn(Collections.singletonList(new PessoaSalarioConsolidado()));

	        when(serviceMock.findUltimoCalculo())
	                .thenReturn(new HistoricoCalculoSalario());
	        
	        mBean.calcular();
	        
	        TimeUnit.SECONDS.sleep(1);
	    
	        verify(serviceMock, times(1)).calcular();
	        verify(serviceMock, atLeastOnce()).consultar(any());
	        verify(serviceMock, atLeastOnce()).findUltimoCalculo();

	        assertFalse(mBean.isEmProcessamento(), "Processamento deveria estar finalizado");
	        assertTrue(mBean.isProcessado(), "Deveria estar marcado como processado");
	        assertNotNull(mBean.getLista(), "Lista de salários não deveria ser nula");
	    }
}
