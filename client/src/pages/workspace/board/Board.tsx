import React, {useEffect, useState} from "react";
import {IconButton, List, ListItem} from '@mui/material';
import {BoardProps} from "./props";
import "./styles.css";
import DeleteForeverIcon from '@mui/icons-material/DeleteForever';
import MutableInput from '../../../component/mutableInput/MutableInput';
import Card from "./card/Card";
import {ICard, ICardCreation} from "../../../model/Card";
import {addCardService, deleteCardService, getCardsService} from "../../../services/card";
import AddCard from "./addCard/AddCard";

const Board: React.FC<BoardProps> = ({board, deleteBoard}: BoardProps) => {
    const [description, setDescription] = useState(board.description);
    const [name, setName] = useState(board.name);
    const [cards, setCards] = useState<ICard[]>([]);

    useEffect(() => {
        getCards(board.id);
    }, [])

    const getCards = (boardId: number) => {
        const fetchedCards = getCardsService(boardId);
        setCards(fetchedCards.filter(card => card.boardId === boardId))    }

    const addCard = (boardId: number, newCard: ICardCreation) => {
        const addedCard = addCardService(boardId, newCard);
        setCards([...cards, addedCard])
    }

    const deleteCard = (id: number) => {
        deleteCardService(id);
        setCards(cards.filter(card => card.id !== id));
    }

    return (
        <div className={"board"}>
            <MutableInput className={"boardTitle"} text={name} placeHolder="" setText={setName}/>
            <MutableInput className={"description"} text={description} placeHolder="" setText={setDescription}/>
            <p className={"deleteButtonWrap"}>
                <IconButton className={"deleteButton"} aria-label="delete" onClick={() => deleteBoard(board.id)}>
                    <DeleteForeverIcon className={"invert"}/>
                </IconButton>
            </p>
            <List>
                {cards.map((card) => (
                    <ListItem className={"cardListItem"} key={card.id}>
                        <Card card={card} deleteCard={deleteCard}/>
                    </ListItem>
                ))}
                <AddCard boardId={board.id} addCard={addCard}/>
            </List>
        </div>
    );
};

export default Board;