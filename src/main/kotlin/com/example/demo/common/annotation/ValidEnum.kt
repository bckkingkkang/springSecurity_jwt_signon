package com.example.demo.common.annotation

import jakarta.validation.Constraint
import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext
import jakarta.validation.Payload
import kotlin.reflect.KClass

@Target(AnnotationTarget.FIELD) // 이 annotation은 필드에만 붙일 수 있음
@Retention(AnnotationRetention.RUNTIME) // 실행 중에도 유지, validator가 런타임에 이 어노테이션을 읽어서 검사 가능
@MustBeDocumented   // 문서화 시 포함
@Constraint(validatedBy = [ValidEnumValidator::class])
annotation class ValidEnum (    // 값이 enum에 속하는지 검증
    // 검증 실패 시 에러 메세지
    val message: String = "Invalid enum value.",
    // 검증 그룹
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = [],
    val enumClass: KClass<out Enum<*>>
)

class ValidEnumValidator : ConstraintValidator<ValidEnum, Any> {
    private lateinit var enumValues: Array<out Enum<*>>

    override fun initialize(constraintAnnotation: ValidEnum) {
        enumValues = constraintAnnotation.enumClass.java.enumConstants
    }
    override fun isValid(value: Any?, context: ConstraintValidatorContext?) : Boolean {
        if(value == null) {
            return true
        }
        return enumValues.any {
            it.name == value.toString()
        }
    }
}