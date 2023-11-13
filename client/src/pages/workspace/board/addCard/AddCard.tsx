import React, {useEffect, useState} from 'react';
import Button from "@mui/material/Button";
import { TextField } from "@mui/material";
import {AddCardProp} from './props';
import {ICardCreation} from "../../../../model/Card";
import "./styles.css";

const AddCard: React.FC<AddCardProp> = ({boardId, addCard}: AddCardProp) => {
    const [card, setCard] = useState<ICardCreation>({name: '', description: ''})
    const [isValid, setIsValid] = useState<boolean>(false)

    useEffect(() => {
        validateCard(card);
    }, [card, boardId]);

    const validateCard = (card: ICardCreation) => {
        if (card.name !== "" && card.description !== "") {
            setIsValid(true)
        } else {
            setIsValid(false)
        }
    }

    const addNewCard = () => {
        addCard(card);
        clearCard();
    }

    const clearCard = () => {
        setCard({name: '', description: ''});
    }

    return (
        <div className={"addCard"}>
            <TextField
                inputProps={{className: "textFieldInputStyle"}}
                className="textField"
                margin="dense"
                variant="outlined"
                label="Name"
                value={card.name}
                onChange={({target: {value}}) => setCard({...card, name: value})}
                type="text"
                fullWidth
            />
            <TextField
                inputProps={{className: "textFieldInputStyle"}}
                className="textField"
                margin="dense"
                variant="outlined"
                label="Description"
                value={card.description}
                onChange={({target: {value}}) => setCard({...card, description: value})}
                type="text"
                fullWidth
            />
            <Button
                className="addButton"
                variant="contained"
                onClick={addNewCard}
                disabled={!isValid}
            >
                <p className="plus">+</p>
            </Button>
        </div>
    );
};

export default AddCard;