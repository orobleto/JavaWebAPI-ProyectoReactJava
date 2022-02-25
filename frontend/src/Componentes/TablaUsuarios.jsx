import React, { Component } from "react";
import DataTable from 'react-data-table-component';


const COLUMNAS = [
    {
        name: "Identificador",
        selector: "id",
        sortable: true
    },
    {
        name: "Nombre",
        selector: "nombre",
        sortable: true
    },
    {
        name: "Apellido",
        selector: "apellido",
        sortable: true
    },
    {
        name: "Tipo de Documento",
        selector: "documento.tipo"
    },
    {
        name: "Numero de Documento",
        selector: "documento.numero",
        right: true
    },
    {
        name: "Correo",
        selector: "correo",
        grow: 2
    },


];

export default class TablaUsuarios extends Component {

    constructor(props) {
        super(props);
        this.state = {
            columnas: COLUMNAS,
            usuarios: [],
            usuariosTabla: [],
            filtro: ""
        }
    }

    componentDidMount() {
        fetch("http://localhost:8080/backend/servicios/usuarios/listar")
            .then(response => response.json())
            .then(result => this.setState({ usuarios: result, usuariosTabla: result }))
            .catch(error => console.log('error', error));
    }


    filtrar = async (e) => {
        await this.setState({ [e.target.name]: e.target.value.toLowerCase() });
        this.buscarDatos();
    }

    buscarDatos = () => {

        this.setState({
            usuariosTabla: this.state.usuarios.filter((e) =>
                (e.nombre.toLowerCase().includes(this.state.filtro) || e.id.toString().includes(this.state.filtro) || e.correo.includes(this.state.filtro))
            )
        });

    }


    render() {
        return (
            <>
                <input
                    type="text"
                    placeholder="Filtrar"
                    name="filtro"
                    id="filtro"
                    value={this.state.filtro}
                    onChange={this.filtrar}
                />

                <DataTable
                    columns={this.state.columnas}
                    data={this.state.usuariosTabla}
                    pagination
                    paginationComponentOptions={
                        {
                            rowsPerPageText: "Filas por pagina",
                            rangeSeparatorText: "de",
                            selectAllRowsItem: true,
                            selectAllRowsItemText: "Todos"

                        }
                    }
                    fixedHeader
                    fixedHeaderScrollHeight="400px"
                    noDataComponent={
                        <spam><strong>Ups...</strong> No hay registros que mostrar </spam>
                    }
                />
            </>
        );
    }

}