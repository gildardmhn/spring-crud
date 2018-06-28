package com.ufc.br.security;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import com.ufc.br.model.Pessoa;
import com.ufc.br.repository.PessoaRepository;


@Transactional
@Repository
public class UserDetailsServiceImplementacao implements UserDetailsService{
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
		Pessoa pessoa = pessoaRepository.findByUsername(username);
		
		if(pessoa == null) {
			throw new UsernameNotFoundException("Usuário não encontrado");
		}
		
		return new User(pessoa.getUsername(),pessoa.getPassword(),true,true,true,true,pessoa.getAuthorities());
	}

}
