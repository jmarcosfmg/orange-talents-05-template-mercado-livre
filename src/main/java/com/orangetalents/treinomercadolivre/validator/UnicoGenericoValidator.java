package com.orangetalents.treinomercadolivre.validator;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UnicoGenericoValidator implements ConstraintValidator<Unico, String> {

	@PersistenceContext
	private EntityManager em;
	private Class<?> classe;
	private String atributo;

	public void initialize(Unico annotation) {
		this.classe = annotation.classe();
		this.atributo = annotation.atributo();
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		try {
			em.createQuery("select 1 from " + this.classe.getName() + " c where lower(c." + this.atributo
					+ ") like lower(:value)").setParameter("value", value).getSingleResult();
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(atributo + " já existente").addConstraintViolation();
			return false;
		} catch (NoResultException e) {
			return true;
		}

	}

}
