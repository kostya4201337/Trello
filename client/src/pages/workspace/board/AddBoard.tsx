import React, {useState} from 'react';
import {BoardEntity} from "../../../model/entity/BoardEntity";
import {BoardCreation} from "../../../model/dto/BoardCreation";
import {AddBoardProp} from "./props";
import Button from "@mui/material/Button";
import "./style.css";

const AddBoard = ({onCreate}: AddBoardProp) => {

    const [post, setPost] = useState<BoardCreation>({name: '', description: ''})

    const addNewBoard = () => {
        const newBoard: BoardEntity = {
            id: Date.now(),
            ...post,
            createdAt: new Date(),
            updatedAt: new Date()
        }
        onCreate(newBoard);
        setPost({name: 'Name', description: 'Description'});
    }

    return (
        <Button
            style={{
                borderRadius: 20,
                backgroundColor: "#ffffff",
                fontSize: "18px"
            }}
            className={"addButton"} variant="contained" onClick={addNewBoard}>
            <p className={"boardTitle"} style={{paddingLeft: 0}}>+</p>
        </Button>
    );
};

export default AddBoard;