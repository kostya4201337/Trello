import React, {useState} from "react";
import {IconButton} from '@mui/material';
import {DeleteBoardProp} from "./props";
import "./styles.css";
import DeleteForeverIcon from '@mui/icons-material/DeleteForever';
import MutableInput from '../../../component/mutableInput/mutableInput';

const Board: React.FC<DeleteBoardProp> = ({board, deleteBoard}: DeleteBoardProp) => {
    const [description, setDescription] = useState(board.description);
    const [name, setName] = useState(board.name);

    return (
        <div className={"board"}>
            <MutableInput className={"boardTitle"} text={name} placeHolder="" textSet={setName}/>
            <MutableInput className={"description"} text={description} placeHolder="" textSet={setDescription}/>
            <p className={"deleteButtonWrap"}>
                <IconButton className={"deleteButton"} aria-label="delete" onClick={() => deleteBoard(board.id)}>
                    <DeleteForeverIcon className={"invert"}/>
                </IconButton>
            </p>
        </div>
    );
};

export default Board;