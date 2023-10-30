import React, {useState} from "react";
import {IconButton} from '@mui/material';
import {DeleteBoardProp} from "./props";
import "./style.css";
import DeleteForeverIcon from '@mui/icons-material/DeleteForever';
import MutableInput from './mutableInput/MutableInput.component';

const Board: React.FC<DeleteBoardProp> = ({board, onDelete}: DeleteBoardProp) => {
    const [description, descriptionSet] = useState(board.description);
    const [name, nameSet] = useState(board.name);

    return (
        <div className={"board"}>
            <div className={"boardTitle"}><MutableInput value={name} placeHolder="Name" valueSet={nameSet}/></div>
            <div className={"description"}><MutableInput value={description} placeHolder="Description" valueSet={descriptionSet}/></div>
            <p className={"deleteButtonWrap"}><IconButton style={
                {
                    backgroundColor: '#302C2C',
                    borderLeft: "2px #575757 solid",
                    borderTop: "2px #575757 solid",
                    borderRadius: 20,
                }
            } className={"deleteButton"}
                        aria-label="delete" onClick={() => onDelete(board.id)}>
                <DeleteForeverIcon className={"invert"}/>
            </IconButton></p>
        </div>
    );
};

export default Board;