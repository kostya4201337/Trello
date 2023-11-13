import React, {useEffect, useState} from 'react';
import {IBoardCreation} from "../../../model/Board";
import {AddBoardProp} from "./props";
import Button from "@mui/material/Button";
import "./styles.css";
import { TextField } from '@mui/material';

const AddBoard: React.FC<AddBoardProp> = ({addBoard}: AddBoardProp) => {
    const [board, setBoard] = useState<IBoardCreation>({name: '', description: ''})
    const [isValid, setIsValid] = useState<boolean>(false)

    useEffect(() => {
        validateBoard(board);
    }, [board]);

    const addNewBoard = () => {
        addBoard(board);
        setBoard({name: '', description: ''});
    }

    const validateBoard = (board: IBoardCreation) => {
        if (board.name !== "" && board.description !== "") {
            setIsValid(true)
        } else {
            setIsValid(false)
        }
    }

    return (
        <div className="board">
            <TextField
                inputProps={{className: "textFieldInputStyle"}}
                className="textField"
                margin="dense"
                variant="outlined"
                label="Name"
                value={board.name}
                onChange={({target: {value}}) => setBoard({...board, name: value})}
                type="text"
                fullWidth
            />
            <TextField
                inputProps={{className: "textFieldInputStyle"}}
                className="textField"
                margin="dense"
                variant="outlined"
                label="Description"
                value={board.description}
                onChange={({target: {value}}) => setBoard({...board, description: value})}
                type="text"
                fullWidth
            />
            <Button
                className="addButton"
                variant="contained"
                onClick={addNewBoard}
                disabled={!isValid}
            >
                <p className="plus">+</p>
            </Button>
        </div>
    );
};

export default AddBoard;