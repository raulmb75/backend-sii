package com.sii.aspirantes.aspirantes.Entity;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name = "sol_ficha_examen")
@AllArgsConstructor
@NoArgsConstructor
@DynamicUpdate
@DynamicInsert
@Builder
public class Aspirante {
    @Id
    @SequenceGenerator(
            name = "aspirante_sequence",
            sequenceName = "aspirante_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "aspirante_sequence"
    )
    @Column(name = "no_solicitud")
    private Long noSolicitud;
    @Column(name = "periodo",length = 5)
    private Integer periodo; //¿Se relaciona con otra tabla? NO, pero podría
    @Column(name= "nombre_aspirante",length = 50)
    private String nombreAspirante;
    @Column(name= "apellido_paterno",length = 50)
    private String apellidoPaterno;
    @Column(name= "apellido_materno",length = 50)
    private String apellidoMaterno;
    @Column(name= "nip",nullable = false)
    private Integer nip;
    @Column(name= "fecha_nacimiento")
    private Date fechaNacimiento;
    @Column(name= "estado_civil", length = 15)
    private String estadoCivil;
    @Column(name= "genero")
    private Character genero; // H - M
    @Column(name = "nacionalidad")
    private String nacionalidad;
    @Column(name= "curp",length = 18, nullable = false, unique = true)
    private String curp;
    @JoinColumn(name = "carrera_opcion_1", referencedColumnName = "reticula") //1 - 7
    @ManyToOne()
    private Carrera carreraOp1;
    @JoinColumn(name = "carrera_opcion_2", referencedColumnName = "reticula") //1 - 7
    @ManyToOne()
    private Carrera carreraOp2;
    @JoinColumn(name = "clave_preparatoria", referencedColumnName = "clave_preparatoria")
    @ManyToOne()
    private PreparatoriasProcedencia clavePreparatoria;
    @Column(name= "entidad_federativa_prepa",length = 50)
    private Integer entidadFederativaPrepa;
    @Column(name= "anio_egreso")
    private Integer anioEgreso;
    @Column(name = "promedio_general")
    private Double promedioGeneral;
    @Column(name = "calle_no")
    private String calleNo;
    @JoinColumn(name = "entidad_federativa", referencedColumnName = "entidad_federativa")
    @ManyToOne(optional = true)
    private EntidadFederativa entidadFederativa;
    @Column(name = "municipio", length = 50)
    private String municipio; //Revisar tipo de dato
    @Column(name= "codigo_postal",length = 7)
    private String codigoPostal;
    @Column(name= "colonia_aspirante",length = 30)
    private String coloniaAspirante;
    @Column(name= "correo_electronico",length = 50, nullable = false, unique = true)
    private String correoElectronico;
    @Column(name= "telefono",length = 10, nullable = false)
    private String telefono;
    @Column(name = "capacidad_diferente")
    private String capacidadDiferente;
    @Column(name = "tiene_beca")
    private Character tieneBeca; // S = sí - N = no
    @Column(name= "quien_otorgo",length = 30)
    private String quienOtorgo;
    @Column(name= "zona_procedencia",length = 18)
    private String zonaProcedencia;
    @Column(name= "especifique_zona_procedencia",length = 18)
    private String especifiqueZonaProcedencia;
    @Column(name= "programa_gubernamental",length = 100)
    private String programaGubernamental;

    // DATOS PADRE DEL ASPIRANTE
    @Column(name= "apellido_paterno_padre",length = 50)
    private String apellidoPaternoPadre;
    @Column(name= "apellido_materno_padre",length = 50)
    private String apellidoMaternoPadre;
    @Column(name= "nombre_padre_aspirante",length = 50)
    private String nombrePadreAspirante;
    @Column(name = "vive_padre")
    private Character vivePadre; //S = sí, N = no

    // DATOS MADRE DEL ASPIRANTE
    @Column(name= "apellido_paterno_madre",length = 50)
    private String apellidoPaternoMadre;
    @Column(name= "apellido_materno_madre",length = 50)
    private String apellidoMaternoMadre;
    @Column(name= "nombre_madre_aspirante",length = 50)
    private String nombreMadreAspirante;
    @Column(name = "vive_madre")
    private Character viveMadre; //S = sí, N = no

    // OTROS DATOS
    @Column(name= "fecha_atencion",length = 15,updatable = false)
    private String fechaAtencion; //23-02-2023
    @Column(name= "hora_atencion",length = 8,updatable = false)
    private String horaAtencion; // 23:05:23
    @Column(name = "itmin")
    private String itmin; // ¿Qué es? Puede permanecer nulo (No funciona para nada)
    @Column(name = "folio_ceneval")
    private Integer folioCeneval;
    @Column(name = "fecha_pago")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaPago;
    @Column(name= "no_recibo",nullable = true)
    private Long noRecibo;
    @Column(name = "instituto")
    private String instituto;
    @Column(name = "especifique_extrangero")
    private String especifiqueExtrangero;
    @Column(name= "fecha_registro",updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;
    @Column(name = "estatus_ficha", length = 20)
    private String estatusFicha; // Activada / No activada
    @Column(name = "aceptado")
    private boolean aceptado; //True - False
    @Column(name = "estatus_pago", length = 15)
    private String estatusPago;
    @Column(name = "estatus_examen", length = 15)
    private String estatusExamen; // NO PRESENTADO - PENDIENTE - APROBADO - NO APROBADO
    private String aula;
    @JoinColumn(name = "rol", referencedColumnName = "idrol")
    @ManyToOne()
    private Roles rol;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "aspirantes_notificaciones",
            joinColumns = @JoinColumn(name = "no_solicitud"),
            inverseJoinColumns = @JoinColumn(name = "id"))
    private Set<Notificaciones> notificaciones = new HashSet<>(); //No puede tener mensajes repetidos



}
