import React, {useEffect, useState} from "react";
import {IconButton, List, ListItem} from '@mui/material';
import {BoardProps} from "./props";
import "./styles.css";
import DeleteForeverIcon from '@mui/icons-material/DeleteForever';
import MutableInput from '../../../component/mutableInput/MutableInput';
import Card from "./card/Card";
import {ICard, ICardCreation} from "../../../model/Card";
import {
    addCardService,
    deleteCardService,
    getCardsByBoardIdService,
    updateCardService
} from "../../../services/card";
import AddCard from "./addCard/AddCard";

const Board: React.FC<BoardProps> = ({board, deleteBoard, updateName, updateDescription}: BoardProps) => {
    const [description, setDescription] = useState(board.description);
    const [name, setName] = useState(board.name);
    const [cards, setCards] = useState<ICard[]>([]);

    useEffect(() => {
        getCards().then();
    }, [board.id])

    const getCards = async () => {
        const fetchedCards = await getCardsByBoardIdService(board.id);
        setCards(fetchedCards);
    }

    const addCard = async (newCard: ICardCreation) => {
        const addedCard = await addCardService(board.id, newCard);
        if (addedCard !== null) {
            setCards([...cards, addedCard]);
        }
    }

    const deleteCard = async (cardId: number) => {
        await deleteCardService(board.id, cardId);
        await getCards();
    }

    const updateCardName = async (card: ICard, text: string) => {
        await updateCardService(
            card.boardId,
            card.id,
            {name: text, description: card.description}
        );
    }

    const updateCardDescription = async (card: ICard, text: string) => {
        await updateCardService(
            card.boardId,
            card.id,
            {name: card.name, description: text}
        );
    }

    return (
        <div className={"board"}>
            <MutableInput className={"boardTitle"} text={name} placeHolder="" setText={setName} onChange={(name) => updateName(board, name)}/>
            <MutableInput className={"description"} text={description} placeHolder="" setText={setDescription} onChange={(description) => updateDescription(board, description)}/>
            <p className={"deleteButtonWrap"}>
                <IconButton className={"deleteButton"} aria-label="delete" onClick={() => deleteBoard(board.id)}>
                    <DeleteForeverIcon className={"invert"}/>
                </IconButton>
            </p>
            <List className={"cardsList"}>
                {cards.map((card) => (
                    <ListItem className={"cardListItem"} key={card.id}>
                        <Card card={card} deleteCard={deleteCard} updateName={updateCardName} updateDescription={updateCardDescription}/>
                    </ListItem>
                ))}
            </List>
            <AddCard boardId={board.id} addCard={addCard}/>
        </div>
    );
};

export default Board;