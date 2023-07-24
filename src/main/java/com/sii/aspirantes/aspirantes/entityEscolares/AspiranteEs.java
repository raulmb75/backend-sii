package com.sii.aspirantes.aspirantes.entityEscolares;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


@Entity
@Data
@Table(name = "sol_ficha_examen")
@AllArgsConstructor
@NoArgsConstructor
@DynamicUpdate
@DynamicInsert
@Builder
public class AspiranteEs {

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
  private Long periodo; //¿Se relaciona con otra tabla? NO
  @Column(name= "nombre_aspirante",length = 50)
  private String nombreAspirante;
  @Column(name= "apellido_paterno",length = 50)
  private String apellidoPaterno;
  @Column(name= "apellido_materno",length = 50)
  private String apellidoMaterno;
  @Column(name= "nip",nullable = false)
  private Long nip;
  @Column(name= "fecha_nacimiento")
  private Date fechaNacimiento;
  @Column(name= "estado_civil", length = 15)
  private String estadoCivil;
  @Column(name= "genero")
  private char genero; // H - M
  @Column(name = "nacionalidad")
  private String nacionalidad;
  @Column(name= "curp",length = 18, nullable = false, unique = true)
  private String curp;
  @Column(name = "carrera_opcion_1",length = 50)
  private String carreraOp1;
  @Column(name = "carrera_opcion_2",length = 50)
  private String carreraOp2;
  @Column(name= "clave_preparatoria",length = 10)
  private String clavePreparatoria;
  @Column(name= "entidad_federativa_prepa",length = 50)
  private String entidadFederativaPrepa;
  @Column(nullable = true, name= "anio_egreso")
  private Long anioEgreso;
  @Column(name = "promedio_general")
  private BigDecimal promedioGeneral;
  @Column(name = "calle_no")
  private String calleNo;
  @Column(name= "entidad_federativa",length = 50)
  private String entidadFederativa; //Revisar tipo de dato
  @Column(name = "municipio", length = 50)
  private String municipio; //Revisar tipo de dato
  @Column(name= "codigo_postal",length = 7)
  private String codigoPostal;
  @Column(name= "colonia_aspirante",length = 50)
  private String coloniaAspirante;
  @Column(name= "correo_electronico",length = 50, nullable = false, unique = true)
  private String correoElectronico;
  @Column(name= "telefono",length = 10, nullable = false)
  private String telefono;
  @Column(name = "capacidad_diferente")
  private String capacidadDiferente;
  @Column(name = "tiene_beca")
  private char tieneBeca; // S = sí - N = no
  @Column(name= "quien_otorgo",length = 50)
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
  private char vivePadre; //S = sí, N = no

  // DATOS MADRE DEL ASPIRANTE
  @Column(name= "apellido_paterno_madre",length = 50)
  private String apellidoPaternoMadre;
  @Column(name= "apellido_materno_madre",length = 50)
  private String apellidoMaternoMadre;
  @Column(name= "nombre_madre_aspirante",length = 50)
  private String nombreMadreAspirante;
  @Column(name = "vive_madre")
  private char viveMadre; //S = sí, N = no

  // OTROS DATOS
  @Column(name= "fecha_atencion",length = 15,updatable = false)
  private String fechaAtencion; //23-02-2023
  @Column(name= "hora_atencion",length = 8,updatable = false)
  private String horaAtencion; // 23:05:23
  @Column(name = "itmin")
  private String itmin; // ¿Qué es? Puede permanecer nulo
  @Column(name = "folio_ceneval")
  private Long folioCeneval;
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


}
