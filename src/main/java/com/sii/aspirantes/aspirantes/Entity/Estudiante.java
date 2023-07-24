package com.sii.aspirantes.aspirantes.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "estudiante")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Estudiante {
    @Id
    @Column(name = "no_de_control", length = 11)
    private String noDeControl;
    @Column(name = "carrera", length = 3)
    private String carrera;
    @Column(name = "especialidad", length = 3)
    private String especialidad;
    @Column(name = "nivel_escolar")
    private Character nivelEscolar;
    @Column(name = "semestre")
    private Integer semestre;
    @Column(name = "estatus_alumno", length = 3)
    private String estatusAlumno;
    @Basic(optional = false)
    @Column(name = "plan_de_estudios")
    private Character planDeEstudios;
    @Column(name = "apellido_paterno", length = 30)
    private String apellidoPaterno;
    @Column(name = "apellido_materno", length = 30)
    private String apellidoMaterno;
    @Basic(optional = false)
    @Column(name = "nombre_alumno", length = 40)
    private String nombreAlumno;
    @Column(name = "curp_alumno", length = 18)
    private String curpAlumno;
    @Column(name = "fecha_nacimiento")
    @Temporal(TemporalType.DATE)
    private Date fechaNacimiento;
    @Column(name = "sexo")
    private Character sexo;
    @Column(name = "estado_civil")
    private Character estadoCivil;
    @Column(name = "ultimo_periodo_inscrito", length = 5)
    private String ultimoPeriodoInscrito;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "promedio_periodo_anterior")
    private Double promedioPeriodoAnterior;
    @Column(name = "promedio_aritmetico_acumulado")
    private Double promedioAritmeticoAcumulado;
    @Column(name = "creditos_aprobados")
    private Integer creditosAprobados;
    @Column(name = "creditos_cursados")
    private Integer creditosCursados;
    @Column(name = "tipo_servicio_medico")
    private Character tipoServicioMedico;
    @Column(name = "clave_servicio_medico", length = 20)
    private String claveServicioMedico;
    @Column(name = "escuela_procedencia", length = 100) //Revisar tipo de dato
    private String escuelaProcedencia;
    @Column(name = "domicilio_escuela", length = 60)
    private String domicilioEscuela;
    @Column(name = "entidad_procedencia")
    private String entidadProcedencia; //Revisar tipo de dato
    @Column(name = "ciudad_procedencia", length = 50)
    private String ciudadProcedencia;
    @Column(name = "correo_electronico", length = 60)
    private String correoElectronico;
    @Column(name = "foto", length = 20)
    private String foto;
    @Column(name = "firma", length = 60)
    private String firma;
    @Column(name = "nip")
    private Integer nip;
    @Column(name = "usuario", length = 30)
    private String usuario;
    @Column(name = "fecha_actualizacion")
    @Temporal(TemporalType.DATE)
    private Date fechaActualizacion;
    @JoinColumn(name = "reticula", referencedColumnName = "reticula")
    @ManyToOne
    private Carrera reticula;
    @JoinColumn(name = "periodo_ingreso_it", referencedColumnName = "periodo")
    @ManyToOne
    private PeriodoEscolar periodoIngresoIt;
}
