package com.orangetalents.treinomercadolivre.validator;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CampoVerificadoValidator implements ConstraintValidator<CampoVerificado, Long> {

	@PersistenceContext
	private EntityManager em;

	private Class<?> classe;
	private String atributo;

	public void initialize(CampoVerificado annotation) {
		this.classe = annotation.classe();
		this.atributo = annotation.atributo();
	}

	@Override
	public boolean isValid(Long value, ConstraintValidatorContext context) {
		try {
			em.createQuery("select 1 from " + this.classe.getName() + " c where c." + this.atributo + " = :value")
					.setParameter("value", value).getSingleResult();
			return true;
		} catch (NoResultException e) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate("Não foi encontrado " + this.classe.getSimpleName() + " com "
					+ this.atributo + " " + value.toString()).addConstraintViolation();
			return false;
		}

	}

}
