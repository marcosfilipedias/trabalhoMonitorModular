package com.trabalho.modular.cadastroMonitor.dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.trabalho.modular.cadastroMonitor.models.Aluno;

public class AlunoDao implements GenericDao<Aluno, Integer>{
	static final String ARCHIVE = "jogadores.txt";
	static final String SEQUENCE = "sequence.txt";

	@Override
	public Aluno get(Integer id) {
		Aluno res = null;
		Aluno a = null;
		
		try(BufferedReader  ler = new BufferedReader(new FileReader(ARCHIVE))){
			String line;
			
			while((line = ler.readLine())!=null) {
				String data[] = line.split(";");
				
				a = new Aluno();
				a.setMatricula(Integer.parseInt(data[0]));
				a.setHistorico(data[1]);
				a.setCurriculo(Boolean.parseBoolean(data[2]));
				
				if (id.equals(a.getMatricula())) {
					res = a;
					break;
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public boolean create(Aluno t) {
		try {
			BufferedReader seq = new BufferedReader(new FileReader(SEQUENCE));
			BufferedWriter lerAluno = new BufferedWriter(new FileWriter(ARCHIVE, true));

			Integer generatedId;
			String linha = seq.readLine();
			if (linha != null) {
				generatedId = Integer.parseInt(linha);
				seq.close();

				BufferedWriter outSeq = new BufferedWriter(new FileWriter(SEQUENCE, false));
				outSeq.write(Integer.toString(generatedId + 1));
				outSeq.flush();
				outSeq.close();
			} else {
				generatedId = 1;

				BufferedWriter outSeq = new BufferedWriter(new FileWriter(SEQUENCE, false));
				outSeq.write(Integer.toString(generatedId + 1));
				outSeq.flush();
				
				String separadorDeAtributo = ";";
				
				lerAluno.write(generatedId + separadorDeAtributo);
				lerAluno.write(t.getHistorico() + separadorDeAtributo);
				lerAluno.write(t.getCurriculo() + separadorDeAtributo);
				lerAluno.write(System.getProperty("line.separator"));
				lerAluno.flush();
				
				lerAluno.write(System.getProperty("line.separator"));
				lerAluno.flush();
				
				outSeq.close();
				lerAluno.close();
			} 
			
		}catch (Exception e) {

				e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public List<Aluno> read() throws FileNotFoundException, NumberFormatException, IOException {
		List<Aluno> alunos = new ArrayList<Aluno>();
		Aluno a = null;
		BufferedReader entrada = new BufferedReader(new FileReader(ARCHIVE));
		String linha;

		while ((linha = entrada.readLine()) != null) {
			String[] dados = linha.split(";");

			a = new Aluno();
			a.setMatricula(Integer.parseInt(dados[0]));
			a.setHistorico(dados[1]);
			a.setCurriculo(Boolean.parseBoolean(dados[2]));
			alunos.add(a);
		}

		entrada.close();
		return alunos;
	}

	@Override
	public void update(Aluno t) throws NumberFormatException, IOException {
		List<Aluno> aluno = read();
		int index = aluno.indexOf(t);
		if (index != -1) {
			aluno.set(index, t);
			saveToFile(aluno);
		}
	}

	@Override
	public void delete(Aluno t) throws NumberFormatException, IOException {
		List<Aluno> alunos = read();
		int index = alunos.indexOf(t);
		if (index != -1) {
			alunos.remove(index);
			saveToFile(alunos);
		}
		
	}
	
	public void saveToFile(List<Aluno> list) throws IOException {
		BufferedWriter saida = new BufferedWriter(new FileWriter(ARCHIVE, false));
		String separador = ";";
		for (Aluno a : list) {
			saida.write(a.getMatricula() + separador);
			saida.write(a.getHistorico() + separador);
			saida.write(a.getCurriculo() + separador);
			
			saida.write(System.getProperty("line.separator"));
			saida.flush();
		}
		saida.close();
	}

}
