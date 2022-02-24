import React, { Component } from "react";
import DataTable from 'react-data-table-component';


const COLUMNAS = [
    {
        name: "Identificador",
        selector: "nombre",
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
        right:true
    },
    {
        name: "Correo",
        selector: "correo",
        grow:2
    },


];

export default class TablaUsuarios extends Component {

    constructor(props) {
        super(props);
        this.state = {
            columnas: COLUMNAS,
            usuarios: []
        }
    }

    componentDidMount() {
       fetch("http://localhost:8080/backend/servicios/usuarios/listar")
            .then(response => response.json())
            .then(result => this.setState({ usuarios: result }))
            .catch(error => console.log('error', error));
    }


    render() {
        return (
            <>
                <input type="text"  placeholder="Filtrar"/>


                <DataTable
                    columns={this.state.columnas}
                    data={this.state.usuarios}
                    pagination
                    paginationComponentOptions={
                        {
                            rowsPerPageText:"Filas por pagina",
                            rangeSeparatorText:"de",
                            selectAllRowsItem:true,
                            selectAllRowsItemText:"Todos"
                            
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