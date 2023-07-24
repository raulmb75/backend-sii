package com.sii.aspirantes.aspirantes.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

import java.util.Date;

@Entity
@Table(name = "personal")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@DynamicUpdate
public class Personal {

    @Id
    @Basic(optional = false)
    @Column(name = "rfc", length = 13)
    private String rfc;
    @Column(name = "clave_centro_seit")
    private String claveCentroSeit;
    @JoinColumn(name = "clave_area", referencedColumnName = "clave_area")
    @ManyToOne(optional = false)
    private Organigrama claveArea;
    //
    @Basic(optional = false)
    @Column(name = "curp_empleado")
    private String curpEmpleado;
    @Column(name = "no_tarjeta")
    private Integer noTarjeta;
    @Column(name = "apellidos_empleado")
    private String apellidosEmpleado;
    @Column(name = "nombre_empleado")
    private String nombreEmpleado;
    @Column(name = "horas_nombramiento")
    private Integer horasNombramiento;
    @Basic(optional = false)
    @Column(name = "nombramiento")
    private String nombramiento;
    @Column(name = "clases")
    private String clases;
    @Column(name = "ingreso_rama")
    private String ingresoRama;
    @Column(name = "inicio_gobierno")
    private String inicioGobierno;
    @Column(name = "inicio_sep")
    private String inicioSep;
    @Column(name = "inicio_plantel")
    private String inicioPlantel;
    @Column(name = "domicilio_empleado")
    private String domicilioEmpleado;
    @Column(name = "colonia_empleado")
    private String coloniaEmpleado;
    @Column(name = "codigo_postal_empleado")
    private Integer codigoPostalEmpleado;
    @Column(name = "localidad")
    private String localidad;
    @Column(name = "telefono_empleado")
    private String telefonoEmpleado;
    @Column(name = "sexo_empleado")
    private Character sexoEmpleado;
    @Column(name = "estado_civil")
    private String estadoCivil;
    @Column(name = "fecha_nacimiento")
    @Temporal(TemporalType.DATE)
    private Date fechaNacimiento;
    @Column(name = "lugar_nacimiento")
    private Integer lugarNacimiento;
    @Column(name = "institucion_egreso")
    private String institucionEgreso;
    @Column(name = "nivel_estudios")
    private String nivelEstudios;
    @Column(name = "grado_maximo_estudios")
    private String gradoMaximoEstudios;
    @Column(name = "estudios")
    @Temporal(TemporalType.TIMESTAMP)
    private Date estudios;
    @Column(name = "fecha_titulacion")
    @Temporal(TemporalType.DATE)
    private Date fechaTitulacion;
    @Column(name = "cedula_profesional")
    private String cedulaProfesional;
    @Column(name = "especializacion")
    private String especializacion;
    @Column(name = "idiomas_domina")
    private String idiomasDomina;
    @Column(name = "status_empleado")
    private String statusEmpleado;
    @Column(name = "foto")
    private String foto;
    @Column(name = "firma")
    private String firma;
    @Column(name = "correo_electronico")
    private String correoElectronico;
    @Column(name = "padre")
    private String padre;
    @Column(name = "madre")
    private String madre;
    @Column(name = "conyuge")
    private String conyuge;
    @Column(name = "hijos")
    private Integer hijos;
    @Column(name = "titulo_num")
    private String tituloNum;
    @Column(name = "titulo_num_libro")
    private String tituloNumLibro;
    @Column(name = "titulo_num_foja")
    private Integer tituloNumFoja;
    @Column(name = "titulo_num_anio")
    private Integer tituloNumAnio;
    @Column(name = "num_cartilla_smn")
    private String numCartillaSmn;
    @Column(name = "anio_clase")
    private Integer anioClase;
    @Column(name = "pigmentacion")
    private String pigmentacion;
    @Column(name = "pelo")
    private String pelo;
    @Column(name = "frente")
    private String frente;
    @Column(name = "cejas")
    private String cejas;
    @Column(name = "ojos")
    private String ojos;
    @Column(name = "nariz")
    private String nariz;
    @Column(name = "boca")
    private String boca;
    @Column(name = "estatura_mts")
    private Integer estaturaMts;
    @Column(name = "peso_kg")
    private Integer pesoKg;
    @Column(name = "senas_visibles")
    private String senasVisibles;
    @Column(name = "pais")
    private String pais;
    @Column(name = "pasaporte")
    private Integer pasaporte;
    @Column(name = "fm")
    private String fm;
    @Column(name = "observaciones_empleado")
    private String observacionesEmpleado;
    @Column(name = "area_academica")
    private String areaAcademica;
    @Column(name = "tipo_personal")
    private String tipoPersonal;
    @Column(name = "tipo_control")
    private String tipoControl;
    @Column(name = "emergencia")
    private String emergencia;
    @Column(name = "entidad_federativa")
    private Integer entidadFederativa;
    @Column(name = "municipio_empleado")
    private String municipioEmpleado;
    @Column(name = "nacionalidad")
    private String nacionalidad;
    @Column(name = "acta_nacimiento_numero")
    private Integer actaNacimientoNumero;
    @Column(name = "acta_nacimiento_foja")
    private Integer actaNacimientoFoja;
    @Column(name = "acta_nacimiento_libro")
    private String actaNacimientoLibro;
    @Column(name = "acta_nacimiento_anio")
    private Integer actaNacimientoAnio;
    @Column(name = "pasaporte_vigencia_inicio")
    @Temporal(TemporalType.TIMESTAMP)
    private Date pasaporteVigenciaInicio;
    @Column(name = "pasaporte_vigencia_fin")
    @Temporal(TemporalType.TIMESTAMP)
    private Date pasaporteVigenciaFin;
    @Column(name = "pasaporte_puesto_autorizado")
    private String pasaportePuestoAutorizado;
    @Column(name = "otros_dependientes")
    private Integer otrosDependientes;
    @Column(name = "instituto_titulacion")
    private String institutoTitulacion;
    @Column(name = "anio_inicio_estudios")
    private Integer anioInicioEstudios;
    @Column(name = "anio_termino_estudios")
    private Integer anioTerminoEstudios;
    @Column(name = "domicilio_empleado_numero")
    private String domicilioEmpleadoNumero;
    @Column(name = "fecha_cedula")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCedula;
    @Column(name = "institucion_exipide_titulo")
    private String institucionExipideTitulo;
    @Column(name = "institucion_expide_cedula")
    private String institucionExpideCedula;
    @Column(name = "inactivo_rc")
    private String inactivoRc;
    @Column(name = "documento_obtenido")
    private String documentoObtenido;
    @Column(name = "documento_obtenido_fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date documentoObtenidoFecha;
    @Column(name = "documento_obtenido_institucion")
    private String documentoObtenidoInstitucion;
    @Column(name = "apellido_paterno")
    private String apellidoPaterno;
    @Column(name = "apellido_materno")
    private String apellidoMaterno;
    @Column(name = "tit_abreviado")
    private String titAbreviado;
    @Column(name = "estudios1")
    private String estudios1;
}
